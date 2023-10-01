package com.naicson.yugioh.mocks;


import cardscommons.dto.CardYuGiOhAPI;

public class CardYuGiOhAPIMock {
	
	public static CardYuGiOhAPI createCardYuGiOh() {
		CardYuGiOhAPI obj = new CardYuGiOhAPI();
		
		obj.setArchetype("Archetype");
		obj.setAtk(1500);
		obj.setAttribute("EARTH");
		obj.setDesc("Dexcription");
		obj.setId(1L);
		obj.setLevel(8);
		obj.setLinkval(3);
		obj.setName("Name");
		obj.setRace("Dragon");
		obj.setType("DECK");
		
		return obj;
	}
}	
