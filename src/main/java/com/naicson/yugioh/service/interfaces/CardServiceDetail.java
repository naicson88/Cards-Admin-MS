package com.naicson.yugioh.service.interfaces;

import java.util.List;

import com.naicson.yugioh.entity.CardYuGiOhAPI;
import com.naicson.yugioh.entity.RelDeckCards;

public interface CardServiceDetail {
	
	Long[] verifyCardsNotRegistered(List<RelDeckCards> listRelDeckCards, String token);
	
	List<Long> getCardsNumberFromListRelDeckCards(List<RelDeckCards> listRelDeckCards);

	List<CardYuGiOhAPI> getCardsToBeRegistered(List<Long> cardsNotRegistered);
}
