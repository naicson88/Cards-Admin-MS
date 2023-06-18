package com.naicson.yugioh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.PriceDTO;
import com.naicson.yugioh.feing.UpdatePrice;

@Service
public class PriceService {
	
	@Autowired
	UpdatePrice request;
	
	 public List<PriceDTO> updateDeckPrice(String deckName){
		 String json = request.getDeckPrice(deckName)
				 .orElseThrow(() -> new NoSuchElementException("Can't find Deck prices!"));
		 
		 List<PriceDTO> listPrices = new ArrayList<>();
		 
		 JSONObject object = (JSONObject) new JSONObject(json).get("data");
		 JSONArray cards = object.getJSONArray("cards");
		 
		 for(int i = 0; i < cards.length(); i++) {
			 JSONObject card = cards.getJSONObject(i);
			 JSONArray numbers = card.getJSONArray("numbers");
			 PriceDTO dto = new PriceDTO();
			 dto.setCardName(card.getString("name"));
			 
			 createPriceObject(listPrices, numbers, dto);	 
		 }
		 
		 return listPrices;
		 
	 }

	private List<PriceDTO> createPriceObject(List<PriceDTO> listPrices, JSONArray numbers, PriceDTO dto) {
		
		for(int j = 0; j < numbers.length(); j++) {
			 JSONObject numberData = numbers.getJSONObject(j);
			 
			 JSONObject priceData =  numberData.getJSONObject("price_data"); //(JSONObject) new JSONObject(card).get("price_data");
			 String status = priceData.getString("status");
			 double price;
			 
			 if("success".equals(status)) {
				 JSONObject data = priceData.getJSONObject("data");
				 JSONObject prices = data.getJSONObject("prices");
				 price = prices.getDouble("average");
			 } else {
				 price = 0.0;
			 }
			 
			 dto.setCardSetCode(numberData.getString("print_tag"));
			 dto.setUpdateTime(new Date());
			 dto.setCardRarity(numberData.getString("rarity"));
			 dto.setPrice(price);
			 
			 listPrices.add(dto);
			 
		 }
		
		return listPrices;
	}
	 

}
