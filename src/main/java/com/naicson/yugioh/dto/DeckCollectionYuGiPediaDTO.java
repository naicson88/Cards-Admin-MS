package com.naicson.yugioh.dto;

import java.util.List;

public class DeckCollectionYuGiPediaDTO extends CollectionDeck{
	
	private static final long serialVersionUID = 1L;
//	private long collectionId;
//	private String deckName;
//	private boolean isBasedDeck;
//	private boolean isSpeedDuel;
	private List<YuGiPediaDataCardsDTO> cards;
	
	public List<YuGiPediaDataCardsDTO> getCards() {
		return cards;
	}
	public void setCards(List<YuGiPediaDataCardsDTO> cards) {
		this.cards = cards;
	}
	
//	public long getCollectionId() {
//		return collectionId;
//	}
//	public void setCollectionId(long collectionId) {
//		this.collectionId = collectionId;
//	}
//	public String getDeckName() {
//		return deckName;
//	}
//	public void setDeckName(String deckName) {
//		this.deckName = deckName;
//	}
//	public boolean isBasedDeck() {
//		return isBasedDeck;
//	}
//	public void setBasedDeck(boolean isBasedDeck) {
//		this.isBasedDeck = isBasedDeck;
//	}
//	public List<YuGiPediaDataCardsDTO> getCards() {
//		return cards;
//	}
//	public void setCards(List<YuGiPediaDataCardsDTO> cards) {
//		this.cards = cards;
//	}
//	
//	@Override
//	public String toString() {
//		return "DeckCollectionYuGiPediaDTO [collectionId=" + collectionId + ", deckName=" + deckName + ", isBasedDeck="
//				+ isBasedDeck + ", cards=" + cards + "]";
//	}
//	public boolean isSpeedDuel() {
//		return isSpeedDuel;
//	}
//	public void setSpeedDuel(boolean isSpeedDuel) {
//		this.isSpeedDuel = isSpeedDuel;
//	}
	
	
	
}
