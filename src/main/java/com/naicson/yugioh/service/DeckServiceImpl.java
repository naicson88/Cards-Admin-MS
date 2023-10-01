package com.naicson.yugioh.service;

import cardscommons.dto.CollectionDeckDTO;
import cardscommons.dto.KonamiDeckDTO;
import cardscommons.dto.RelDeckCardsDTO;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPIDeckAndCardsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(DeckServiceImpl.class);
	
	@Autowired
	YuGiOhAPIDeckAndCardsImpl apiService;
	
	@Autowired
	CardServiceDetailImpl cardService;


	public KonamiDeckDTO createNewKonamiDeckWithCards(KonamiDeckDTO konamiDeck, String token) {
		
		List<RelDeckCardsDTO> listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(konamiDeck.getRequestSource());
		
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
	
	
	public CollectionDeckDTO createNewCollectionDeck(CollectionDeckDTO cDeck, String token) {
		
		if(cDeck.getSetId() == null)
			throw new IllegalArgumentException("Invalid Set Id");
		
		List<RelDeckCardsDTO> listRelDeckCards = getListRelDeckCardsForNewCollectionDeck(cDeck);
		
		//It necessary to check if all cards are already registered in cards' table
		List<Long> listCardsNotRegistered = checkCardsNotRegistered(listRelDeckCards, token);
		
		if(listCardsNotRegistered != null && !listCardsNotRegistered.isEmpty())
			cDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		cDeck.setRelDeckCards(listRelDeckCards);
			
		return cDeck;
					
	}
	
	private List<Long> checkCardsNotRegistered(List<RelDeckCardsDTO> listRelDeckCards, String token) {
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(listRelDeckCards, token);

		return Arrays.asList(cardsNotRegistered).stream().distinct().collect(Collectors.toList());
	}

	private List<RelDeckCardsDTO> getListRelDeckCardsForNewCollectionDeck(CollectionDeckDTO cDeck) {
		
		List<RelDeckCardsDTO> listRelDeckCards;
		if(cDeck.getFilterSetCode() != null && !cDeck.getFilterSetCode().isBlank()) 
			listRelDeckCards = this.getFilteredCards(cDeck);
		else 
			listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource());
		
		return listRelDeckCards;
	}
	
	private List<RelDeckCardsDTO> getFilteredCards(CollectionDeckDTO cDeck) {
		
		List<RelDeckCardsDTO> listRelDeckCards = new ArrayList<>();
		String[] filtersSetCode = cDeck.getFilterSetCode().split(";");
		
		apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource())
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


	public CollectionDeckDTO registerNewDeckCollectionYugipedia(CollectionDeckDTO cDeck, String token) {
		List<Long> listCardsNotRegistered = checkCardsNotRegistered(cDeck.getRelDeckCards(), token);
		
		if(listCardsNotRegistered != null && !listCardsNotRegistered.isEmpty())
			cDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));
		
		cDeck.getRelDeckCards().forEach(rel -> rel.setDt_criacao(new Date()));
			
		return cDeck;
	}

}
