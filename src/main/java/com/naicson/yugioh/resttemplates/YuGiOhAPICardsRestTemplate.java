package com.naicson.yugioh.resttemplates;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YuGiOhAPICardsRestTemplate {
	
	private final String schem = "https";
	private final String host = "db.ygoprodeck.com";
	
	public String getCardFromYuGiOhAPI(Long cardNumber) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(this.schem)
				.host(this.host)	
				.path("api/v7/cardinfo.php")
				.queryParam("id", cardNumber)
				.build();
		
		System.out.println(uri.toString());

		String json = restTemplate.getForObject(uri.toString(), String.class);
		
		return json;		  	
	}
	
	
}
