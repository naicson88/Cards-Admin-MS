package com.naicson.yugioh.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.AddNewCardToDeckDTO;
import com.naicson.yugioh.entity.RelDeckCards;

@Service
public class CardServiceImpl {
	
	@Autowired
	CardServiceDetailImpl cardService;
	
	public AddNewCardToDeckDTO addNewCardToDeck(AddNewCardToDeckDTO card, String token) {
		validNewCard(card);
		
		RelDeckCards rel = new RelDeckCards();
		rel.setCardNumber(card.getNumber());
		
		Long[] cardsNotRegistered = cardService.verifyCardsNotRegistered(List.of(rel), token);
		List<Long> listCardsNotRegistered =  Arrays.asList(cardsNotRegistered);
		
		if(cardsNotRegistered != null && cardsNotRegistered.length > 0)
			card.setCardsToBeRegistered(cardService.getCardsToBeRegistered(listCardsNotRegistered));	
		
		return card;
	}
	
	private void validNewCard(AddNewCardToDeckDTO card) {
		if(card == null)
			throw new IllegalAccessError("Informed Card is invalid");
		
		if(card.getDeckId() == null || card.getDeckId() == 0)
			throw new IllegalAccessError("#validNewCard: Invalid Deck ID");
		if(card.getName() == null || card.getName().isBlank())
			throw new IllegalAccessError("#validNewCard: Invalid Card Name");
		if(card.getNumber() == null || card.getNumber() == 0)
			throw new IllegalAccessError("#validNewCard: Invalid Card Number");
		if(card.getRarity() == null || card.getRarity().isBlank())
			throw new IllegalAccessError("#validNewCard: Invalid Card Rarity");
		if(card.getRarityCode() == null || card.getRarityCode().isBlank())
			throw new IllegalAccessError("#validNewCard: Invalid Card Rarity Code");
		if(card.getRarityDetails() == null || card.getRarityDetails().isBlank())
			throw new IllegalAccessError("#validNewCard: Invalid Card Rarity Details");
		if(card.getCardSetCode() == null || card.getCardSetCode().isBlank())
			throw new IllegalAccessError("#validNewCard: Invalid Card Set Code");
		
	}
}
