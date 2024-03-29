package com.naicson.yugioh.service.yugiohAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cardscommons.dto.CardYuGiOhAPI;
import com.naicson.yugioh.resttemplates.YuGiOhAPICardsRestTemplate;
import com.naicson.yugioh.util.exceptions.ErrorMessage;

@Service
public class YuGiOhAPICardsImpl {
	
	@Autowired
	YuGiOhAPICardsRestTemplate apiCards;
	
	Logger logger = LoggerFactory.getLogger(YuGiOhAPICardsImpl.class);

	public CardYuGiOhAPI consultCardOnYuGiOhAPI(Long cardNumber) {
		String json = apiCards.getCardFromYuGiOhAPI(cardNumber);
		return this.transformJsonInCard(json);
	}
	
	
	private CardYuGiOhAPI transformJsonInCard(String json) {
		
		if(json == null || json.isBlank())
			throw new IllegalArgumentException("Invalid Json informed to transform in Card");	
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		
		JsonNode tree;
		CardYuGiOhAPI card = new CardYuGiOhAPI();
		
		try {
			
			tree = mapper.readTree(json);
			JsonNode node = tree.path("data");
			CardYuGiOhAPI[] cardArr = mapper.readValue(node.toString(), CardYuGiOhAPI[].class);
			
			if(cardArr != null && cardArr.length == 1) {
				card = cardArr[0];
			} else {
				logger.error("It was not possible transform Json in a Card");
				throw new ErrorMessage("It was not possible transform Json in a Card");
			}
			
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
		} catch(ErrorMessage em) {
			logger.error(em.getMsg());
		}
		
		return card;	

	}
	
	
}
