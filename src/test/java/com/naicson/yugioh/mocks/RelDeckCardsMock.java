package com.naicson.yugioh.mocks;

import cardscommons.dto.RelDeckCardsDTO;

import java.util.Date;

public class RelDeckCardsMock {
public static RelDeckCardsDTO relDeckCards() {

	   RelDeckCardsDTO rel = new RelDeckCardsDTO();
		rel.setId(1L);
		rel.setCard_price(1.5);
		rel.setCardNumber(123456L);
		rel.setDt_criacao(new Date());
		rel.setDeckId(1L);
		rel.setIsSideDeck(false);
		rel.setIsSpeedDuel(false);
		rel.setSetRarityCode("SR");
		
		return rel;		
	}

	public static RelDeckCardsDTO relDeckCardsFiltered(String filter) {

		RelDeckCardsDTO rel = new RelDeckCardsDTO();
		rel.setId(1L);
		rel.setCard_price(1.5);
		rel.setCardNumber(123456L);
		rel.setDt_criacao(new Date());
		rel.setDeckId(1L);
		rel.setIsSideDeck(false);
		rel.setIsSpeedDuel(false);
		rel.setSetRarityCode("SR");
		rel.setCardSetCode(filter);
		return rel;	
	}
}
