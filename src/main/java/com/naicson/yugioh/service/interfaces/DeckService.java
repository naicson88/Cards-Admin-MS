package com.naicson.yugioh.service.interfaces;

import java.util.List;

import com.naicson.yugioh.dto.KonamiDeck;

public interface DeckService {
	
	KonamiDeck createNewKonamiDeckWithCards(KonamiDeck konamiDeck, String token);

	KonamiDeck removeCardsBasedOnSetCode(KonamiDeck kDeck);
}
