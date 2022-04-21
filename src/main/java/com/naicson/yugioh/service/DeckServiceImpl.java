package com.naicson.yugioh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

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
		
		if(konamiDeck.getSpecificSetCodes() != null && konamiDeck.getSpecificSetCodes().size() > 0)
			konamiDeck = this.removeCardsBasedOnSetCode(konamiDeck);
		
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
	}
	
	
	@Override
	public KonamiDeck removeCardsBasedOnSetCode(KonamiDeck kDeck) {
		
		if(kDeck.getSpecificSetCodes() == null || kDeck.getSpecificSetCodes().size() == 0) 
			return kDeck;
			
			List<RelDeckCards> newRelDeckCards = new ArrayList<>();
			
			kDeck.getRelDeckCards().stream().forEach(rel -> {
				if(kDeck.getSpecificSetCodes().contains(rel.getCardSetCode().trim()))
					newRelDeckCards.add(rel);				
			});
			
			if(newRelDeckCards != null && newRelDeckCards.size() > 0) {
				kDeck.getRelDeckCards().clear();
				kDeck.setRelDeckCards(newRelDeckCards);
			}
			
		return kDeck;	
		
	}
	
}
