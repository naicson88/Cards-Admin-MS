package com.naicson.yugioh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tab_cards")
public class Card {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long numero;
	private String categoria;
	private String nome;
	private String nomePortugues;
	private String atributo;
	private String propriedade;
	private Integer nivel;
	private String tipo;
	private Integer atk;
	private Integer def;
	private String condicao;
	@Column(columnDefinition="text")
	private String descricao;
	@Column(columnDefinition="text")
	private String descricaoPortugues;
	private String imagem;
	private String raridade;
	private Integer escala;
	@Column(columnDefinition="text")
	private String descr_pendulum;
	@Column(columnDefinition="text")
	private String descr_pendulum_pt;
	private String arquetipo;
	private String qtd_link;
	private String archetype;
	
	public Card() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoPortugues() {
		return descricaoPortugues;
	}

	public void setDescricaoPortugues(String descricaoPortugues) {
		this.descricaoPortugues = descricaoPortugues;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getRaridade() {
		return raridade;
	}

	public void setRaridade(String raridade) {
		this.raridade = raridade;
	}

	public Integer getEscala() {
		return escala;
	}

	public void setEscala(Integer escala) {
		this.escala = escala;
	}

	public String getDescr_pendulum() {
		return descr_pendulum;
	}

	public void setDescr_pendulum(String descr_pendulum) {
		this.descr_pendulum = descr_pendulum;
	}

	public String getDescr_pendulum_pt() {
		return descr_pendulum_pt;
	}

	public void setDescr_pendulum_pt(String descr_pendulum_pt) {
		this.descr_pendulum_pt = descr_pendulum_pt;
	}

	public String getArquetipo() {
		return arquetipo;
	}

	public void setArquetipo(String arquetipo) {
		this.arquetipo = arquetipo;
	}

	public String getQtd_link() {
		return qtd_link;
	}

	public void setQtd_link(String qtd_link) {
		this.qtd_link = qtd_link;
	}

	public String getArchetype() {
		return archetype;
	}

	public void setArchetype(String archetype) {
		this.archetype = archetype;
	}
	
	
}