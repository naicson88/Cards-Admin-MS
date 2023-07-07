package com.naicson.yugioh.dto;

import cardscommons.dto.KonamiDeckDTO;

import java.util.List;

public class KonamiDeckWithDTO {
	
	private KonamiDeckDTO konamiDeck;
	private List<String> cardSetCodes;
	
	public KonamiDeckDTO getKonamiDeck() {
		return konamiDeck;
	}
	public void setKonamiDeck(KonamiDeckDTO konamiDeck) {
		this.konamiDeck = konamiDeck;
	}
	public List<String> getCardSetCodes() {
		return cardSetCodes;
	}
	public void setCardSetCodes(List<String> cardSetCodes) {
		this.cardSetCodes = cardSetCodes;
	}
	
	
	
}
