package com.naicson.yugioh.mocks;

import com.naicson.yugioh.dto.AddNewCardToDeckDTO;

public class AddNewCardToDeckDTOMock {
	
	public static AddNewCardToDeckDTO newCard() {
		AddNewCardToDeckDTO card = new AddNewCardToDeckDTO();
		card.setNumber(123L);
		
		return card;
	}
}
