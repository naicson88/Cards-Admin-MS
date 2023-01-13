package com.naicson.yugioh.dto;

public class YuGiPediaDataCardsDTO {
	
	private int number;
	private String setCode;
	private String name;
	private String rarity;
	
	public String getSetCode() {
		return setCode;
	}
	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	@Override
	public String toString() {
		return "YuGiPediaDataCardsDTO [setCode=" + setCode + ", name=" + name + ", rarity=" + rarity + "]";
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	
}
