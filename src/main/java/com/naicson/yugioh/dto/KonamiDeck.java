package com.naicson.yugioh.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.naicson.yugioh.entity.RelDeckCards;

@Component
public class KonamiDeck implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	@JsonAlias("requestSource")
	private String requestSource;
	private String imagem;
	private String nome;
	private String nomePortugues;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date lancamento;
	private String setType;
	private List<CardYuGiOhAPI> cardsToBeRegistered;
	private List<RelDeckCards> relDeckCards;
	private Boolean isSpeedDuel;
	 
	
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
	public Boolean getIsSpeedDuel() {
		return isSpeedDuel;
	}
	public void setIsSpeedDuel(Boolean isSpeedDuel) {
		this.isSpeedDuel = isSpeedDuel;
	}
	
	

}
