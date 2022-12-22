package com.naicson.yugioh.resttemplates;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.naicson.yugioh.dto.AssociationDTO;

@Component
public class SetCollectionRestTemplate {
	
	private static final String SCHEM = "http";
	private static final String HOST = "localhost:8080";
	
	public ResponseEntity<String> sendNewAssociation(AssociationDTO dto, String token){
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);
		header.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<AssociationDTO> entity = new HttpEntity<>(dto, header);
		
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(SCHEM)
				.host(HOST)	
				.path("yugiohAPI/collection/new-association")
				//.queryParam("cardNumbers", cardsOfDeck)
				.build();
	
		return restTemplate.postForEntity(uri.toString(), entity, String.class);
	}
	
}
