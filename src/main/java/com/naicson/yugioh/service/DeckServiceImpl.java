package com.naicson.yugioh.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		List<RelDeckCards> listRelDeckCards = apiService.consultCardsOfADeckInYuGiOhAPI(konamiDeck.getNome());
		
		if(listRelDeckCards == null || listRelDeckCards.size() == 0) {
			logger.error("Informed Relation Deck x Cards is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Relation Deck x Cards is invalid!");
		}
		
		//It necessary to check if all cards are already registered in cards' table
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(listRelDeckCards, token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0) {

			konamiDeck.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));
		}
		
		konamiDeck.setRelDeckCards(listRelDeckCards);
		
		return konamiDeck;
		
	}
	
	private void validKonamiDeck(KonamiDeck kDeck) {
		
		if(kDeck == null) {
			logger.error("Informed Deck is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck is invalid");
		}
		
		if (kDeck.getImagem() == null || kDeck.getImagem().isEmpty()) {
			logger.error("Informed Deck Image is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck Image is invalid");
		}
		
		if(kDeck.getLancamento() == null || kDeck.getLancamento().after(new Date()) ) {
			logger.error("Informed Deck Lancamento is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck Lancamento is invalid");
		}
		
		if(kDeck.getNome() == null || kDeck.getNome().isEmpty()) {
			logger.error("Informed Deck Name is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck Name is invalid");
		}
		
		if(kDeck.getNomePortugues() == null || kDeck.getNomePortugues().isEmpty()) {
			logger.error("Informed Deck Nome Portugues is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck Nome Portugues is invalid");
		}
		
		if(kDeck.getSetType() == null || kDeck.getSetType().isEmpty()) {
			logger.error("Informed Deck SetType is invalid!".toUpperCase());
			throw new IllegalArgumentException("Informed Deck SetType is invalid");
		}
	
	}
	
}
