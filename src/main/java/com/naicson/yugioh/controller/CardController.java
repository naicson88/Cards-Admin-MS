package com.naicson.yugioh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naicson.yugioh.configs.RabbitMQConstantes;
import cardscommons.dto.AddNewCardToDeckDTO;
import cardscommons.dto.CardYuGiOhAPI;
import com.naicson.yugioh.resttemplates.CardRestTemplate;
import com.naicson.yugioh.service.CardServiceDetailImpl;
import com.naicson.yugioh.service.CardServiceImpl;
import com.naicson.yugioh.service.RabbitMQService;

@RestController
@RequestMapping({ "v1/admin/card" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {
	
	@Autowired
	private CardRestTemplate cardRestTemplate;
	
	@Autowired
	private CardServiceDetailImpl cardServiceDetail;
	
	@Autowired
	private CardServiceImpl cardService;
	
	@Autowired
	private RabbitMQService rabbitService;
	
	@PostMapping("/consult-cards")
	public ResponseEntity<Long[]> consultNotRegisteredCards(@RequestBody List<Long> cardsNumbers,  @RequestHeader("Authorization") String token){
		return cardRestTemplate.findCardsNotRegistered(cardsNumbers, token);
	}
	
	@PostMapping("cards-not-registered")
	public ResponseEntity<List<CardYuGiOhAPI>> consultCardsYuGiOhAPINotRegistered(@RequestBody List<Long> cardsNumbers) {
		
		List<CardYuGiOhAPI> list = this.cardServiceDetail.getCardsToBeRegistered(cardsNumbers);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/add-new-card-to-deck")
	public ResponseEntity<AddNewCardToDeckDTO> addNewCardToDeck(@Valid @RequestBody AddNewCardToDeckDTO card, @RequestHeader("Authorization") String token){
		AddNewCardToDeckDTO cardAdded = cardService.addNewCardToDeck(card, token);
		
		this.rabbitService.sendMessageAsJson(RabbitMQConstantes.CARD_QUEUE, cardAdded);
		
		return new ResponseEntity<>(cardAdded, HttpStatus.CREATED);
		
	}
}
