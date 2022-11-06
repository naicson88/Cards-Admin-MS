package com.naicson.yugioh.resttemplates;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class YuGiOhAPIDeckAndCardsRestTemplate {
	
	private static final String SCHEM = "https";
	private static final String HOST = "db.ygoprodeck.com";
	
	public String getCardsFromSetInYuGiOhAPI(String setName) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(SCHEM)
				.host(HOST)	
				.path("api/v7/cardinfo.php")
				.queryParam("cardset", setName.trim().replaceAll("\\s", "%20"))
				.build();

		return restTemplate.getForObject(uri.toString(), String.class);
		  	
	}
}
