package com.naicson.yugioh.service.yugiohAPI;

import cardscommons.dto.RelDeckCardsDTO;
import com.naicson.yugioh.feing.YuGiOhAPI;
import com.naicson.yugioh.resttemplates.YuGiOhAPIDeckAndCardsRestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class YuGiOhAPIDeckAndCardsImpl {

	private String SET_NAME;

	@Autowired
	YuGiOhAPIDeckAndCardsRestTemplate restTemplate;
	
	@Autowired
	YuGiOhAPI feing;

	public List<RelDeckCardsDTO> consultCardsOfADeckInYuGiOhAPI(String setName) {

		if (setName == null || setName.isBlank())
			throw new IllegalArgumentException("Set Name informed is invalid.");

		this.SET_NAME = setName;

		String json = Optional.of(feing.getCardsFromSetInYuGiOhAPI(setName))
				.orElseThrow(() -> new NoSuchElementException("JSON with deck info was empty"));

		return  Optional.of(this.convertJsonInListOfRelDeckCards(json))
				.orElseThrow(() -> new NoSuchElementException("Cards of this Set were not found"));
	}

	private List<RelDeckCardsDTO> convertJsonInListOfRelDeckCards(String json) {

		if (json == null || json.isBlank())
			throw new IllegalArgumentException("JSON with deck info was empty");

		JSONObject object = new JSONObject(json);
		JSONArray jsonArray = object.getJSONArray("data");

		List<RelDeckCardsDTO> listRelation = new LinkedList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject card = jsonArray.getJSONObject(i);

			List<RelDeckCardsDTO> relation = this.returnARelDeckCardFromAJSONObject(card);

			if (!relation.isEmpty())
				relation.stream().forEach(rel -> listRelation.add(rel));
		 }

		return listRelation;
	}

	private List<RelDeckCardsDTO> returnARelDeckCardFromAJSONObject(JSONObject card) {
		
		List<RelDeckCardsDTO> listRelDeckCards = IntStream.range(0, card.getJSONArray("card_sets").length())
			.mapToObj(i -> card.getJSONArray("card_sets").getJSONObject(i))
			.filter(c -> c.get("set_name").equals(this.SET_NAME))
			.filter(c -> 
					((String) c.get("set_code")).contains("EN") == true ? 
					((String) c.get("set_code")).contains("EN") :
					((String) c.get("set_code")) != null)
			.map(relation -> {
				RelDeckCardsDTO relDeckCards = new RelDeckCardsDTO();
				relDeckCards.setCardNumber(Integer.toUnsignedLong((Integer) card.get("id")));
				relDeckCards.setCard_price(Double.parseDouble((String) relation.get("set_price")));
				relDeckCards.setCard_raridade((String) relation.get("set_rarity"));
				relDeckCards.setSetRarityCode((String) relation.get("set_rarity_code"));
				relDeckCards.setRarityDetails((String) relation.get("set_rarity"));
				relDeckCards.setCardSetCode((String) relation.get("set_code"));
				relDeckCards.setDt_criacao(new Date());
				relDeckCards.setIsSideDeck(false);
				
				return relDeckCards;
			}).distinct()
			  .collect(Collectors.toList());
		
		return this.editSetCodeDuplicated(listRelDeckCards);
	}
	
	private List<RelDeckCardsDTO> editSetCodeDuplicated(List<RelDeckCardsDTO> listRelDeckCards) {
		
		Map<String, Long> mapDuplicated = listRelDeckCards.stream()
		.collect(Collectors.groupingBy(RelDeckCardsDTO::getCardSetCode, Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry -> entry.getValue() > 1L)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		for(Map.Entry<String, Long> map : mapDuplicated.entrySet()) {
			listRelDeckCards.stream().filter(r -> r.getCardSetCode().equals(map.getKey())).forEach(rel -> {
				rel.setCardSetCode(rel.getCardSetCode()+rel.getSetRarityCode());
			});
		}
		
		return listRelDeckCards;
		
	}
	
	@Async
	public void testeAsync() throws InterruptedException {
		for( int i = 0; i < 10; i++ ) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(i);
		}
		
	}

}
