package com.naicson.yugioh.resttemplates;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
		//String encode = URLEncoder.encode(setName.trim(), StandardCharsets.UTF_8).replaceAll("\\+", "%20");
		String formated =  setName.trim().replaceAll("'", "%27");
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(SCHEM)
				.host(HOST)	
				.path("api/v7/cardinfo.php")
				.queryParam("cardset", formated)
				.build();
		
		System.out.println(uri.toString());
		return restTemplate.getForObject(uri.toString(), String.class);
		  	
	}
}
