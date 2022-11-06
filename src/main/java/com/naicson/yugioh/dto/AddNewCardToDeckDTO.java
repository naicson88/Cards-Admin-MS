package com.naicson.yugioh.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddNewCardToDeckDTO {
	
	@NotBlank(message = "Card Name cannot be empty")
	private String name;
	@NotNull(message = "Card Number cannot be empty")
	private Long number;
	@NotNull(message = "Deck ID cannot be empty")
	private Long deckId;
	@NotBlank(message = "Card Rarity cannot be empty")
	private String rarity;
	@NotBlank(message = "Card Rarity Code cannot be empty")
	private String rarityCode;
	@NotBlank(message = "Card Rarity Details cannot be empty")
	private String rarityDetails;
	@NotBlank(message = "Card SetCode cannot be empty")
	private String cardSetCode;
	@NotNull(message = "isSpeedDuel cannot be empty")
	private Boolean isSpeedDuel;
	@NotNull(message = "Price cannot be empty")
	private double price;
	private List<CardYuGiOhAPI> cardsToBeRegistered;
	
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
