package com.naicson.yugioh.mocks;

import cardscommons.dto.KonamiDeckDTO;

public class KonamiDeckMock {
	
	public static KonamiDeckDTO createKonamiDeck() {
		KonamiDeckDTO k = new KonamiDeckDTO();
		k.setRequestSource("Request mock");
		k.setIsSpeedDuel(true);
		
		return k;
	}
	
	
}
