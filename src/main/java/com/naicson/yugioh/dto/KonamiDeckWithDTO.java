package com.naicson.yugioh.dto;

import java.util.List;

public class KonamiDeckWithDTO {
	
	public KonamiDeck konamiDeck;
	public List<String> cardSetCodes;
	
	public KonamiDeck getKonamiDeck() {
		return konamiDeck;
	}
	public void setKonamiDeck(KonamiDeck konamiDeck) {
		this.konamiDeck = konamiDeck;
	}
	public List<String> getCardSetCodes() {
		return cardSetCodes;
	}
	public void setCardSetCodes(List<String> cardSetCodes) {
		this.cardSetCodes = cardSetCodes;
	}
	
	
	
}
