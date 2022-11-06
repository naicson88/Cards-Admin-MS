package com.naicson.yugioh.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naicson.yugioh.configs.RabbitMQConstantes;
import com.naicson.yugioh.dto.CollectionDeck;
import com.naicson.yugioh.dto.KonamiDeck;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.service.DeckServiceImpl;
import com.naicson.yugioh.service.RabbitMQService;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPIDeckAndCardsImpl;

@RestController
@RequestMapping({ "v1/admin/deck" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeckController {
	
	@Autowired
	private RabbitMQService rabbitService;
	
	@Autowired
	private DeckServiceImpl deckService;
	
	@Autowired
	private YuGiOhAPIDeckAndCardsImpl apiService;
	
	Logger logger = LoggerFactory.getLogger(DeckController.class);
	
	
	@PostMapping("/new-deck")
	public ResponseEntity<KonamiDeck> registerNewDeck(@Valid @RequestBody KonamiDeck kDeck, @RequestHeader("Authorization") String token){
		KonamiDeck createdKonamiDeck = deckService.createNewKonamiDeckWithCards(kDeck, token);
		
		this.rabbitService.sendMessageAsJson("DECK", createdKonamiDeck);
		
		return new ResponseEntity<>(createdKonamiDeck, HttpStatus.OK);
	}
	
	@PostMapping("/new-deck-collection")
	public ResponseEntity<CollectionDeck> registerCollectionDeck(@RequestBody CollectionDeck cDeck, @RequestHeader("Authorization") String token){
		CollectionDeck createCollectionDeck = deckService.createNewCollectionDeck(cDeck, token);
		logger.info("DTO: {} ", createCollectionDeck);
		this.rabbitService.sendMessageAsJson(RabbitMQConstantes.DECK_COLLECTION_QUEUE, createCollectionDeck);
		
		return new ResponseEntity<>(createCollectionDeck, HttpStatus.OK);
	}
	
	@GetMapping("/api")
	public List<RelDeckCards> consultingAPI(@RequestParam("setName") String setName){
		return apiService.consultCardsOfADeckInYuGiOhAPI(setName);		
	}
	
}
