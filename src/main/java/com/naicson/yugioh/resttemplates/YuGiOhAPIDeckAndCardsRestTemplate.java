package com.naicson.yugioh.resttemplates;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class YuGiOhAPIDeckAndCardsRestTemplate {
	
	private final String schem = "https";
	private final String host = "db.ygoprodeck.com";
	
	public String getCardsFromSetInYuGiOhAPI(String setName) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(this.schem)
				.host(this.host)	
				.path("api/v7/cardinfo.php")
				.queryParam("cardset", setName.trim().replaceAll("\\s", "%20"))
				.build();
		System.out.println(uri.toString());

		String result = restTemplate.getForObject(uri.toString(), String.class);
		
		return result;
		  	
	}
}
