package com.naicson.yugioh.service.interfaces;

import java.util.List;

import com.naicson.yugioh.entity.RelDeckCards;

public interface YuGiOhAPIDeckAndCards {
	
	List<RelDeckCards> consultCardsOfADeckInYuGiOhAPI(String setName);
}
