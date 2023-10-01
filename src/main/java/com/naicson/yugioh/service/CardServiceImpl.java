package com.naicson.yugioh.service;

import java.util.Arrays;
import java.util.List;

import cardscommons.dto.RelDeckCardsDTO;
import org.springframework.stereotype.Service;

import cardscommons.dto.AddNewCardToDeckDTO;
import cardscommons.dto.SetCollectionDTO;

@Service
public class CardServiceImpl {
	
	CardServiceDetailImpl cardService;
	
	public CardServiceImpl(CardServiceDetailImpl cardService) {
		this.cardService = cardService;
	}
	
	public AddNewCardToDeckDTO addNewCardToDeck(AddNewCardToDeckDTO card, String token) {
		
		RelDeckCardsDTO rel = new RelDeckCardsDTO();
		rel.setCardNumber(card.getNumber());
		
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(List.of(rel), token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0)
			card.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		return card;
	}
	
}
