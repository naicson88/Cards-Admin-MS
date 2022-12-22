package com.naicson.yugioh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.CollectionDeck;
import com.naicson.yugioh.dto.KonamiDeck;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.service.interfaces.DeckService;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPIDeckAndCardsImpl;

@Service
public class DeckServiceImpl implements DeckService {
	
	Logger logger = LoggerFactory.getLogger(DeckServiceImpl.class);
	
	@Autowired
	YuGiOhAPIDeckAndCardsImpl apiService;
	
	@Autowired
	CardServiceDetailImpl cardService;

	@Override
	public KonamiDeck createNewKonamiDeckWithCards(KonamiDeck konamiDeck, String token) {
		
		List<RelDeckCards> listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(konamiDeck.getRequestSource());
		
		if(listRelDeckCards == null || listRelDeckCards.isEmpty())	
			throw new IllegalArgumentException("Informed Relation Deck x Cards is invalid!");
				
		//It necessary to check if all cards are already registered in cards' table
		List<Long> listCardsNotRegistered = checkCardsNotRegistered(listRelDeckCards, token);
		
		if(listCardsNotRegistered != null && !listCardsNotRegistered.isEmpty())
			konamiDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		konamiDeck.setRelDeckCards(listRelDeckCards);
		
		konamiDeck.getRelDeckCards().forEach(rel -> rel.setIsSpeedDuel(konamiDeck.getIsSpeedDuel()));
			
		return konamiDeck;		
	}
	
	
	public CollectionDeck createNewCollectionDeck(CollectionDeck cDeck, String token) {
		
		if(cDeck.getSetId() == null)
			throw new IllegalArgumentException("Invalid Set Id");
		
		List<RelDeckCards> listRelDeckCards = getListRelDeckCardsForNewCollectionDeck(cDeck);
		
		//It necessary to check if all cards are already registered in cards' table
		List<Long> listCardsNotRegistered = checkCardsNotRegistered(listRelDeckCards, token);
		
		if(listCardsNotRegistered != null && !listCardsNotRegistered.isEmpty())
			cDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		cDeck.setRelDeckCards(listRelDeckCards);
			
		return cDeck;
					
	}
	
	private List<Long> checkCardsNotRegistered(List<RelDeckCards> listRelDeckCards, String token) {
		
		 Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(listRelDeckCards, token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered)
				.stream().distinct().collect(Collectors.toList());
		
		return listCardsNotRegistered;
	}

	private List<RelDeckCards> getListRelDeckCardsForNewCollectionDeck(CollectionDeck cDeck) {
		
		List<RelDeckCards> listRelDeckCards;
		if(cDeck.getFilterSetCode() != null && !cDeck.getFilterSetCode().isBlank()) {
			listRelDeckCards = this.getFilteredCards(cDeck);
		} else {
			listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource());
		}
		return listRelDeckCards;
	}
	
	private List<RelDeckCards> getFilteredCards(CollectionDeck cDeck) {
		
		List<RelDeckCards> listRelDeckCards = new ArrayList<>();
		String[] filtersSetCode = cDeck.getFilterSetCode().split(";");
		
		apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource())
		.stream()
		.forEach(rel -> {
			for(String set : filtersSetCode) {
				if(rel.getCardSetCode().contains(set)) {
					listRelDeckCards.add(rel);
					break;
				}				
			}
		});
		
		return listRelDeckCards;
	}
	
}
