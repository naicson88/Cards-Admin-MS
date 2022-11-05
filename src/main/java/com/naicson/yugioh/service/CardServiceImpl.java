package com.naicson.yugioh.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.AddNewCardToDeckDTO;
import com.naicson.yugioh.entity.RelDeckCards;

@Service
public class CardServiceImpl {
	
	CardServiceDetailImpl cardService;
	RelDeckCards rel;
	
	public CardServiceImpl(CardServiceDetailImpl cardService, RelDeckCards rel) {
		this.cardService = cardService;
		this.rel = rel;
	}
	
	public AddNewCardToDeckDTO addNewCardToDeck(AddNewCardToDeckDTO card, String token) {
		
		//RelDeckCards rel = new RelDeckCards();
		rel.setCardNumber(card.getNumber());
		
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(List.of(rel), token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0)
			card.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		return card;
	}
	
}
