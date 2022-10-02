package com.naicson.yugioh.dto;

import java.util.List;

public class AddNewCardToDeckDTO {

	private String name;
	private Long number;
	private Long deckId;
	private String rarity;
	private String rarityCode;
	private String rarityDetails;
	private String cardSetCode;
	private Boolean isSpeedDuel;
	private double price;
	private List<CardYuGiOhAPI> cardsToBeRegistered;
	
	public AddNewCardToDeckDTO() {}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Long getDeckId() {
		return deckId;
	}
	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getRarityCode() {
		return rarityCode;
	}
	public void setRarityCode(String rarityCode) {
		this.rarityCode = rarityCode;
	}
	public String getRarityDetails() {
		return rarityDetails;
	}
	public void setRarityDetails(String rarityDetails) {
		this.rarityDetails = rarityDetails;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public List<CardYuGiOhAPI> getCardsToBeRegistered() {
		return cardsToBeRegistered;
	}

	public void setCardsToBeRegistered(List<CardYuGiOhAPI> cardsToBeRegistered) {
		this.cardsToBeRegistered = cardsToBeRegistered;
	}

	public String getCardSetCode() {
		return cardSetCode;
	}

	public void setCardSetCode(String cardSetCode) {
		this.cardSetCode = cardSetCode;
	}

	public Boolean getIsSpeedDuel() {
		return isSpeedDuel;
	}

	public void setIsSpeedDuel(Boolean isSpeedDuel) {
		this.isSpeedDuel = isSpeedDuel;
	}
	
	
}