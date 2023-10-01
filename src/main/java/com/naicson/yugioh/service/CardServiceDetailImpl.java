package com.naicson.yugioh.service;

import cardscommons.dto.CardYuGiOhAPI;
import cardscommons.dto.RelDeckCardsDTO;
import com.naicson.yugioh.resttemplates.CardRestTemplate;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPICardsImpl;
import com.naicson.yugioh.util.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CardServiceDetailImpl {

	@Autowired
	CardRestTemplate cardRestTemplate;
	
	@Autowired
	YuGiOhAPICardsImpl apiCards;

	Logger logger = LoggerFactory.getLogger(CardServiceDetailImpl.class);


	public Long[] verifyCardsNotRegistered(List<RelDeckCardsDTO> listRelDeckCards, String token) {

		ResponseEntity<Long[]> cardsNotRegistered = null;

			this.validVerifyCardsNotRegistered(listRelDeckCards, token);

			List<Long> cardNumbersOfDeck = this.getCardsNumberFromListRelDeckCards(listRelDeckCards);

			cardsNotRegistered = cardRestTemplate.findCardsNotRegistered(cardNumbersOfDeck, token);

			if (cardsNotRegistered == null || !cardsNotRegistered.getStatusCode().is2xxSuccessful())
				throw new ErrorMessage("It was not possible verify Card Not Registered");

		return cardsNotRegistered.getBody();

	}

	private void validVerifyCardsNotRegistered(List<RelDeckCardsDTO> listRelDeckCards, String token) {
		if (listRelDeckCards == null || listRelDeckCards.isEmpty()) 
			throw new IllegalArgumentException("Invalid KonamiDeck informed.");
		
		if (token == null || token.isEmpty()) 
			throw new IllegalArgumentException("Invalid Token");
	}
	

	public List<Long> getCardsNumberFromListRelDeckCards(List<RelDeckCardsDTO> listRelDeckCards) {

		List<Long> cardNumbers = listRelDeckCards.stream().filter(rel -> rel.getCardNumber() != null)
				.distinct()
				.map(RelDeckCardsDTO::getCardNumber)
				.collect(Collectors.toList());

		if (cardNumbers.isEmpty())
			throw new IllegalArgumentException("It was not possible get Card Numbers");

		return cardNumbers;

	}

	public List<CardYuGiOhAPI> getCardsToBeRegistered(List<Long> cardsNotRegistered) {
		
		if(cardsNotRegistered == null || cardsNotRegistered.isEmpty())
			throw new IllegalArgumentException("Invalid Cards Not Registered");
				
		List<CardYuGiOhAPI> cardsToBeRegistered = new LinkedList<>();
		
		for(Long n : cardsNotRegistered) {
			CardYuGiOhAPI card = apiCards.consultCardOnYuGiOhAPI(n);
			cardsToBeRegistered.add(card);
		}				
		
		return cardsToBeRegistered;
	}
	
	
}
