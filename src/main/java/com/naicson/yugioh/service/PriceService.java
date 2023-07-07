package com.naicson.yugioh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import cardscommons.dto.PriceDTO;
import com.naicson.yugioh.util.consts.PriceAPISetListNames;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.feing.UpdatePrice;

@Service
public class PriceService {
	
	@Autowired
	UpdatePrice request;
	
	Logger logger = LoggerFactory.getLogger(PriceService.class); 
	
	 public List<PriceDTO> updateDeckPrice(String deckName){
		 logger.info(" Updating Deck Price: {}", deckName);
		 String setNameFromApiList = this.getNameInPriceSetListNames(deckName);
		 logger.info(" Name in API List: {} ", setNameFromApiList);

		 String json = request.getDeckPrice(setNameFromApiList)
				 .orElseThrow(() -> new NoSuchElementException("Can't find Deck prices!"));
		 
		 JSONObject object = (JSONObject) new JSONObject(json).get("data");
		 JSONArray cards = object.getJSONArray("cards");

		 if(cards == null || cards.isEmpty())
			 throw new NoSuchElementException("Cannot find Set price with name: " + deckName);

		 List<PriceDTO> listPrices = new ArrayList<>();
		 
		 for(int i = 0; i < cards.length(); i++) {
			 JSONObject card = cards.getJSONObject(i);
			 JSONArray numbers = card.getJSONArray("numbers");
			 createPriceObject(listPrices, numbers, card.getString("name"));
		 }
		 return listPrices;
	 }
	
	public List<PriceDTO> updateCardPrice(String cardName){
		logger.info(" Updating Card Price: {}", cardName);
		 
		String json = request.getCardPrice(cardName)
				.orElseThrow(() -> new NoSuchElementException("Can't find Card's prices!"));
		
		List<PriceDTO> listPrices = new ArrayList<>();
		JSONObject object = new JSONObject(json);
		JSONArray cards = object.getJSONArray("data"); 
		createPriceObject(listPrices, cards, cardName);	

		return listPrices;
		
	} 
	 
	private List<PriceDTO> createPriceObject(List<PriceDTO> listPrices, JSONArray numbers, String name) {
		
		for(int j = 0; j < numbers.length(); j++) {
			 JSONObject numberData = numbers.getJSONObject(j);
			 
			 JSONObject priceData =  numberData.getJSONObject("price_data"); //(JSONObject) new JSONObject(card).get("price_data");
			 String status = priceData.getString("status");
			 double price;
			 
			 if("success".equals(status)) {
				 JSONObject data = priceData.getJSONObject("data");
				 JSONObject prices = data.getJSONObject("prices");
				 price = prices.getDouble("average");
			 } else 
				 price = 0.0;
			 			
			 listPrices.add(createPriceDTOObject(name, numberData, price));
			 
		 }
		
		return listPrices;
	}

	private PriceDTO createPriceDTOObject(String name, JSONObject numberData, double price) {
		PriceDTO dto = new PriceDTO();
		 dto.setCardName(name);
		 dto.setCardSetCode(numberData.getString("print_tag"));
		 dto.setUpdateTime(new Date());
		 dto.setCardRarity(numberData.getString("rarity"));
		 dto.setPrice(price);
		return dto;
	}

	private String getNameInPriceSetListNames(String originalName) {
		 return PriceAPISetListNames.listSetNames
				.stream()
				.filter(name -> name.contains(originalName))
				.findFirst()
				 .get();

	}

}
