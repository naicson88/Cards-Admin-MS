package com.naicson.yugioh.mocks;

import java.util.Date;

import com.naicson.yugioh.entity.RelDeckCards;

public class RelDeckCardsMock {
public static RelDeckCards relDeckCards() {
		
		RelDeckCards rel = new RelDeckCards();
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

	public static RelDeckCards relDeckCardsFiltered(String filter) {
		
		RelDeckCards rel = new RelDeckCards();
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
