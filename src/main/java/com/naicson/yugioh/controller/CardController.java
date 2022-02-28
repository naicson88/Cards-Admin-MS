package com.naicson.yugioh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naicson.yugioh.dto.CardYuGiOhAPI;
import com.naicson.yugioh.resttemplates.CardRestTemplate;
import com.naicson.yugioh.service.CardServiceDetailImpl;

@RestController
@RequestMapping({ "v1/admin/card" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {
	
	@Autowired
	private CardRestTemplate cardRestTemplate;
	
	@Autowired
	private CardServiceDetailImpl cardService;
	
	@PostMapping("/consult-cards")
	public ResponseEntity<Long[]> consultNotRegisteredCards(@RequestBody List<Long> cardsNumbers,  @RequestHeader("Authorization") String token){
		ResponseEntity<Long[]> entity = cardRestTemplate.findCardsNotRegistered(cardsNumbers, token);
		
		return entity;
	}
	
	@PostMapping("cards-not-registered")
	public ResponseEntity<List<CardYuGiOhAPI>> consultCardsYuGiOhAPINotRegistered(@RequestBody List<Long> cardsNumbers) {
		
		List<CardYuGiOhAPI> list = this.cardService.getCardsToBeRegistered(cardsNumbers);
		
		return new ResponseEntity<List<CardYuGiOhAPI>>(list, HttpStatus.OK);
	}
}
