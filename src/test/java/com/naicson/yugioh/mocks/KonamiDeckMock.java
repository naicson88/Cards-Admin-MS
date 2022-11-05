package com.naicson.yugioh.mocks;

import com.naicson.yugioh.dto.KonamiDeck;

public class KonamiDeckMock {
	
	public static KonamiDeck createKonamiDeck() {
		KonamiDeck k = new KonamiDeck();
		k.setRequestSource("Request mock");
		k.setIsSpeedDuel(true);
		
		return k;
	}
	
	
}
