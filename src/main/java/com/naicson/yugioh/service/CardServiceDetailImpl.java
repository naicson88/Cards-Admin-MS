package com.naicson.yugioh.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.CardYuGiOhAPI;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.resttemplates.CardRestTemplate;
import com.naicson.yugioh.service.interfaces.CardServiceDetail;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPICardsImpl;


@Service
public class CardServiceDetailImpl implements CardServiceDetail {

	@Autowired
	CardRestTemplate cardRestTemplate;
	
	@Autowired
	YuGiOhAPICardsImpl apiCards;

	Logger logger = LoggerFactory.getLogger(CardServiceDetailImpl.class);

	@Override
	public Long[] verifyCardsNotRegistered(List<RelDeckCards> listRelDeckCards, String token) {

		ResponseEntity<Long[]> cardsNotRegistered = null;

			this.validVerifyCardsNotRegisteredMethod(listRelDeckCards, token);

			List<Long> cardNumbersOfDeck = this.getCardsNumberFromListRelDeckCards(listRelDeckCards);

			cardsNotRegistered = cardRestTemplate.findCardsNotRegistered(cardNumbersOfDeck, token);

			if (cardsNotRegistered == null || !cardsNotRegistered.getStatusCode().is2xxSuccessful())
				throw new RuntimeException("It was not possible verify Card Not Registered");

		return cardsNotRegistered.getBody();

	}

	private void validVerifyCardsNotRegisteredMethod(List<RelDeckCards> listRelDeckCards, String token) {
		if (listRelDeckCards == null || listRelDeckCards.isEmpty()) 
			throw new IllegalArgumentException("Invalid KonamiDeck informed.");
		
		if (token == null || token.isEmpty()) 
			throw new IllegalArgumentException("Invalid Token");
	}
	
	@Override
	public List<Long> getCardsNumberFromListRelDeckCards(List<RelDeckCards> listRelDeckCards) {

		List<Long> cardNumbers = listRelDeckCards.stream().filter(rel -> rel.getCardNumber() != null)
				.map(RelDeckCards::getCardNumber).collect(Collectors.toList());

		if (cardNumbers != null && cardNumbers.size() == 0) {
			logger.error("It was not possible get Card Numbers");
			throw new IllegalArgumentException("It was not possible get Card Numbers");
		}

		return cardNumbers;

	}

	
	@Override
	public List<CardYuGiOhAPI> getCardsToBeRegistered(List<Long> cardsNotRegistered) {
		
		if(cardsNotRegistered == null || cardsNotRegistered.size() == 0)
			throw new IllegalArgumentException("Invalid Cards Not Registered");
				
		List<CardYuGiOhAPI> cardsToBeRegistered = new LinkedList<>();
		
		for(Long n : cardsNotRegistered) {
			CardYuGiOhAPI card = apiCards.consultCardOnYuGiOhAPI(n);
			cardsToBeRegistered.add(card);
		}				
		
		return cardsToBeRegistered;
	}
	
	
}
