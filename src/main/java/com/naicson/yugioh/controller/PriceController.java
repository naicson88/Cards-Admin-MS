package com.naicson.yugioh.controller;

import java.util.List;

import cardscommons.dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naicson.yugioh.configs.RabbitMQConstantes;
import com.naicson.yugioh.service.PriceService;
import com.naicson.yugioh.service.RabbitMQService;

@RestController
@RequestMapping({ "v1/admin/price" })
@CrossOrigin(origins = "*", maxAge = 3600)


public class PriceController {
	@Autowired
	PriceService service;
	
	@Autowired
	private RabbitMQService rabbitService;
	
	@GetMapping("/update-deck-price")
	public ResponseEntity<List<PriceDTO>> updateDeckPrice(@RequestParam String deckName) {
		List<PriceDTO> list = service.updateDeckPrice(deckName);
		
		this.rabbitService.sendMessageAsJson(RabbitMQConstantes.SET_PRICE_QUEUE, list);
		
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	
	@GetMapping("/update-card-price")
	public ResponseEntity<List<PriceDTO>> updateCardPrice(@RequestParam String cardName) {
		List<PriceDTO> prices = service.updateCardPrice(cardName);

		this.rabbitService.sendMessageAsJson(RabbitMQConstantes.SET_PRICE_QUEUE, prices);

		return new ResponseEntity<>(prices, HttpStatus.OK);
	}
}
