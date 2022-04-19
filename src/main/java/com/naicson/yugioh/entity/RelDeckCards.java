package com.naicson.yugioh.entity;

import java.io.Serializable;
import java.util.Date;

public class RelDeckCards implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long deckId;
	private Long cardNumber;
	private String cardSetCode;
	private Double card_price;
	private String card_raridade;
	private Date dt_criacao;
	private Boolean isSideDeck;
	private boolean isSpeedDuel;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDeckId() {
		return deckId;
	}
	public void setDeckId(Long deck_id) {
		this.deckId = deck_id;
	}

	public String getCard_set_code() {
		return cardSetCode;
	}
	public void setCard_set_code(String card_set_code) {
		this.cardSetCode = card_set_code;
	}
	public Double getCard_price() {
		return card_price;
	}
	public void setCard_price(Double card_price) {
		this.card_price = card_price;
	}
	public String getCard_raridade() {
		return card_raridade;
	}
	public void setCard_raridade(String card_raridade) {
		this.card_raridade = card_raridade;
	}
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public Boolean getIsSideDeck() {
		return isSideDeck;
	}
	public void setIsSideDeck(Boolean isSideDeck) {
		this.isSideDeck = isSideDeck;
	}

	public boolean getIsSpeedDuel() {
		return isSpeedDuel;
	}
	public void setIsSpeedDuel(boolean isSpeedDuel) {
		this.isSpeedDuel = isSpeedDuel;
	}	
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardSetCode() {
		return cardSetCode;
	}
	public void setCardSetCode(String cardSetCode) {
		this.cardSetCode = cardSetCode;
	}
	public void setSpeedDuel(boolean isSpeedDuel) {
		this.isSpeedDuel = isSpeedDuel;
	}
	
	
	@Override
	public String toString() {
		return "RelDeckCards [id=" + id + ", deckId=" + deckId + ", cardNumber=" + cardNumber + ", cardSetCode="
				+ cardSetCode + ", card_price=" + card_price + ", card_raridade=" + card_raridade + ", dt_criacao="
				+ dt_criacao + ", isSideDeck=" + isSideDeck + ", isSpeedDuel=" + isSpeedDuel + "]";
	}
	
	
	
}
