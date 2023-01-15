package com.naicson.yugioh.mocks;

import com.naicson.yugioh.dto.CollectionDeck;

public class CollectionDeckMock {
	
	public static CollectionDeck createCollectionDeck() {
		CollectionDeck cDeck = new CollectionDeck();
		cDeck.setSetId(1);
		cDeck.setIsSpeedDuel(true);
		cDeck.setFilterSetCode(null);
		
		return cDeck;
	}
	
	public static CollectionDeck createCollectionDeckFiltered() {
		CollectionDeck cDeck = new CollectionDeck();
		cDeck.setSetId(1);
		cDeck.setRequestSource("Any Request");
		cDeck.setIsSpeedDuel(true);
		cDeck.setFilterSetCode(null);
		cDeck.setFilterSetCode("ABCD");
		
		return cDeck;
	}
}
