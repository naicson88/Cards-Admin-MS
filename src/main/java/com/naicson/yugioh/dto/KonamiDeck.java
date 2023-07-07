package com.naicson.yugioh.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.naicson.yugioh.entity.RelDeckCards;
import cardscommons.dto.CardYuGiOhAPI;

@Component
public class KonamiDeck implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank(message = "Request Source cannot be empty")
	@JsonAlias("requestSource")
	private String requestSource;
	@NotBlank(message = "Image url cannot be empty")
	private String imagem;
	@NotBlank(message = "Nome cannot be empty")
	private String nome;
	//@NotBlank(message = "Portuguese Name cannot be empty")
	private String nomePortugues;
	@NotNull(message = "Release date cannot be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date lancamento;
	@NotBlank(message = "SetType cannot be empty")
	private String setType;
	@NotNull(message = "isSpeedDuel cannot be empty")
	private boolean isSpeedDuel;
	@NotNull(message = "isBasedDeck cannot be empty")
	@JsonAlias("isBasedDeck")
	private boolean isBasedDeck;
	@NotBlank(message = "SetCode cannot be empty")
	private String setCode;
	private String description;
	
	private List<CardYuGiOhAPI> cardsToBeRegistered;
	private List<RelDeckCards> relDeckCards;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomePortugues() {
		return nomePortugues;
	}
	public void setNomePortugues(String nomePortugues) {
		this.nomePortugues = nomePortugues;
	}
	public Date getLancamento() {
		return lancamento;
	}
	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}
	public String getSetType() {
		return setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}
	
	public List<RelDeckCards> getRelDeckCards() {
		return relDeckCards;
	}
	public void setRelDeckCards(List<RelDeckCards> relDeckCards) {
		this.relDeckCards = relDeckCards;
	}
	public List<CardYuGiOhAPI> getCardsToBeRegistered() {
		return cardsToBeRegistered;
	}
	public void setCardsToBeRegistered(List<CardYuGiOhAPI> cardsToBeRegistered) {
		this.cardsToBeRegistered = cardsToBeRegistered;
	}

	public String getRequestSource() {
		return requestSource;
	}
	public void setRequestSource(String requestSource) {
		this.requestSource = requestSource;
	}
	public boolean getIsSpeedDuel() {
		return isSpeedDuel;
	}
	public void setIsSpeedDuel(boolean isSpeedDuel) {
		this.isSpeedDuel = isSpeedDuel;
	}
	public boolean getIsBasedDeck() {
		return isBasedDeck;
	}
	public void setIsBasedDeck(boolean isBasedDeck) {
		this.isBasedDeck = isBasedDeck;
	}
	public String getSetCode() {
		return setCode;
	}
	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
