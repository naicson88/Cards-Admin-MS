package com.naicson.yugioh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naicson.yugioh.resttemplates.CardRestTemplate;

@RestController
@RequestMapping({ "v1/admin/card" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {
	
	@Autowired
	private CardRestTemplate cardRestTemplate;
	
	@PostMapping("/consult-cards")
	public ResponseEntity<Long[]> consultNotRegisteredCards(@RequestBody List<Long> cardsNumbers,  @RequestHeader("Authorization") String token){
		ResponseEntity<Long[]> entity = cardRestTemplate.findCardsNotRegistered(cardsNumbers, token);
		
		return entity;
	}
}
