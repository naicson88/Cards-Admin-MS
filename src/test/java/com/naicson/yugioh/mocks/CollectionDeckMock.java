package com.naicson.yugioh.mocks;

import cardscommons.dto.CollectionDeckDTO;

public class CollectionDeckMock {
	
	public static CollectionDeckDTO createCollectionDeck() {
		CollectionDeckDTO cDeck = new CollectionDeckDTO();
		cDeck.setSetId(1);
		cDeck.setIsSpeedDuel(true);
		cDeck.setFilterSetCode(null);
		
		return cDeck;
	}
	
	public static CollectionDeckDTO createCollectionDeckFiltered() {
		CollectionDeckDTO cDeck = new CollectionDeckDTO();
		cDeck.setSetId(1);
		cDeck.setRequestSource("Any Request");
		cDeck.setIsSpeedDuel(true);
		cDeck.setFilterSetCode(null);
		cDeck.setFilterSetCode("ABCD");
		
		return cDeck;
	}
}
