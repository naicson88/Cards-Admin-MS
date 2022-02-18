package com.naicson.yugioh.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.resttemplates.YuGiOhAPIDeckAndCardsRestTemplate;
import com.naicson.yugioh.service.interfaces.YuGiOhAPIDeckAndCards;

@Service
public class YuGiOhAPIDeckAndCardsImpl implements YuGiOhAPIDeckAndCards{
	
	private String SET_NAME;
	
	@Autowired
	YuGiOhAPIDeckAndCardsRestTemplate restTemplate;
	
	
	@Override
	public List<RelDeckCards> consultCardsOfADeckInYuGiOhAPI(String setName) {
		
		if(setName == null || setName.isBlank()) {
			throw new IllegalArgumentException("Set Name informed is invalid.");
		}
		
		this.SET_NAME = setName;
		
		String json = restTemplate.getCardsFromSetInYuGiOhAPI(setName);

		if(json == null || json.isEmpty() || json.isBlank()) {
			throw new NoSuchElementException("JSON with deck info was empty");
		}
		
		List<RelDeckCards> listRelDeckCards = this.convertJsonInListOfRelDeckCards(json);
		
		if(listRelDeckCards == null || listRelDeckCards.isEmpty()) {
			throw new NoSuchElementException("Cards of this Set were not found");
		}
		
		return listRelDeckCards;
	}
	
	private List<RelDeckCards> convertJsonInListOfRelDeckCards(String json) {
		
		if(json == null || json.isEmpty() || json.isBlank()) {
			throw new NoSuchElementException("JSON with deck info was empty");
		}
		
		JSONObject object = new JSONObject(json);
		JSONArray jsonArray = object.getJSONArray("data");
		
		List<RelDeckCards> listRelation = new LinkedList<>();
		
		for(int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject card = jsonArray.getJSONObject(i);
			
			RelDeckCards relation = this.returnARelDeckCardFromAJSONObject(card);
			
			if(relation == null) {
				throw new NoSuchElementException("Relation Deck x Cards is empty");
			}
			
			listRelation.add(relation);
		}
		
		return listRelation;
	}
	
	
	private RelDeckCards returnARelDeckCardFromAJSONObject(JSONObject card) {
		RelDeckCards relDeckCards = new RelDeckCards();
		
		for(int i = 0; i < card.getJSONArray("card_sets").length(); i++) {
			
			JSONObject relation = card.getJSONArray("card_sets").getJSONObject(i);
			String setName = (String) relation.get("set_name");

			if(setName.equalsIgnoreCase(this.SET_NAME)){
				
				relDeckCards.setCard_numero(Integer.toUnsignedLong((Integer) card.get("id")));
				relDeckCards.setCard_price(Double.parseDouble((String) relation.get("set_price")));
				relDeckCards.setCard_raridade((String) relation.get("set_rarity"));
				relDeckCards.setCard_set_code((String) relation.get("set_code"));
				relDeckCards.setDt_criacao(new Date());
				relDeckCards.setIsSideDeck(false);
			}				
		}
		
		return relDeckCards;
	}

}
