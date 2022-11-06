package com.naicson.yugioh.resttemplates;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YuGiOhAPICardsRestTemplate {
	
	private static final String SCHEM = "https";
	private static final String HOST = "db.ygoprodeck.com";
	
	public String getCardFromYuGiOhAPI(Long cardNumber) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(SCHEM)
				.host(HOST)	
				.path("api/v7/cardinfo.php")
				.queryParam("id", cardNumber)
				.build();

		return restTemplate.getForObject(uri.toString(), String.class);	  	
	}
	
	
}
