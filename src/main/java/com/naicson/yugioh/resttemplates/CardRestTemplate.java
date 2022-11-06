package com.naicson.yugioh.resttemplates;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CardRestTemplate {
	
	private static final String SCHEM = "http";
	private static final String HOST = "localhost:8080";
	
	public ResponseEntity<Long[]> findCardsNotRegistered(List<Long> cardsOfDeck, String token){
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);
		header.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<List<Long>> entity = new HttpEntity<>(cardsOfDeck, header);
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(SCHEM)
				.host(HOST)	
				.path("yugiohAPI/cards/search-cards-not-registered")
				.queryParam("cardNumbers", cardsOfDeck)
				.build();
	
		return restTemplate.postForEntity(uri.toString(), entity, Long[].class);
	}
}
