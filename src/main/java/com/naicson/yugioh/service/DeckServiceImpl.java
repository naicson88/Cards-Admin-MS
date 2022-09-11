package com.naicson.yugioh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		this.validKonamiDeck(konamiDeck);
		
		List<RelDeckCards> listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(konamiDeck.getRequestSource());
		
		if(listRelDeckCards == null || listRelDeckCards.size() == 0)	
			throw new IllegalArgumentException("Informed Relation Deck x Cards is invalid!");
				
		//It necessary to check if all cards are already registered in cards' table
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(listRelDeckCards, token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0)
			konamiDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		konamiDeck.setRelDeckCards(listRelDeckCards);
		
		if(konamiDeck.getIsSpeedDuel())
			konamiDeck.getRelDeckCards().forEach(rel ->rel.setIsSpeedDuel(true));
		else
			konamiDeck.getRelDeckCards().forEach(rel ->rel.setIsSpeedDuel(false));
			
		return konamiDeck;
		
	}
	
	private void validKonamiDeck(KonamiDeck kDeck) {
		
		if(kDeck == null) 
			throw new IllegalArgumentException("Informed Deck is invalid");
			
		if (kDeck.getImagem() == null || kDeck.getImagem().isEmpty()) 			
			throw new IllegalArgumentException("Informed Deck Image is invalid");		
		
		if(kDeck.getLancamento() == null || kDeck.getLancamento().after(new Date()) ) 
			throw new IllegalArgumentException("Informed Deck Lancamento is invalid");		
		
		if(kDeck.getNome() == null || kDeck.getNome().isEmpty()) 			
			throw new IllegalArgumentException("Informed Deck Name is invalid");		
		
		if(kDeck.getNomePortugues() == null || kDeck.getNomePortugues().isEmpty()) 		
			throw new IllegalArgumentException("Informed Deck Nome Portugues is invalid");		
		
		if(kDeck.getSetType() == null || kDeck.getSetType().isEmpty()) 			
			throw new IllegalArgumentException("Informed Deck SetType is invalid");	
		
		if(kDeck.getIsSpeedDuel() == null)
			kDeck.setIsSpeedDuel(false);
	}
	
	public CollectionDeck createNewCollectionDeck(CollectionDeck cDeck, String token) {
		
		List<RelDeckCards> listRelDeckCards = new ArrayList<>();
		
		if(cDeck.getFilterSetCode() != null && !cDeck.getFilterSetCode().isBlank()) {
			listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource())
					.stream()
					.filter(rel -> rel.getCardSetCode().contains(cDeck.getFilterSetCode()))
					.collect(Collectors.toList());
		} else {
			listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource());
		}
		
		//It necessary to check if all cards are already registered in cards' table
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(listRelDeckCards, token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0)
			cDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		cDeck.setRelDeckCards(listRelDeckCards);
		
		if(cDeck.getIsSpeedDuel())
			cDeck.getRelDeckCards().forEach(rel ->rel.setIsSpeedDuel(true));
		else
			cDeck.getRelDeckCards().forEach(rel ->rel.setIsSpeedDuel(false));
			
		return cDeck;
					
	}
	
}
