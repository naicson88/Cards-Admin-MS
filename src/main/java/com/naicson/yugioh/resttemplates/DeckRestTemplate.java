package com.naicson.yugioh.resttemplates;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.naicson.yugioh.dto.KonamiDeck;

@Service
public class DeckRestTemplate {
	
	private final String schem = "http";
	private final String host = "localhost:8080";
	

	
	public ResponseEntity<KonamiDeck> getKonamiDeck(Long deckId, String source, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(this.schem)
				.host(this.host)	
				.path("yugiohAPI/decks")
				.queryParam("id", deckId)
				.queryParam("source", source)
				.build();
		
		 ResponseEntity<KonamiDeck> entity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, 
				 new HttpEntity<Object>(header), KonamiDeck.class);
		
		 
		 
		 /*ResponseEntity<YourResponseObj> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/youruri", HttpMethod.GET, new HttpEntity<Object>(headers),
                YourResponseObj.class);*/
		  
		  return entity;
	}

}
