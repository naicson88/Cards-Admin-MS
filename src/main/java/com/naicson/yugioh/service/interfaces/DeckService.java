package com.naicson.yugioh.service.interfaces;

import com.naicson.yugioh.dto.KonamiDeck;

public interface DeckService {
	
	KonamiDeck createNewKonamiDeckWithCards(KonamiDeck konamiDeck, String token);
}
