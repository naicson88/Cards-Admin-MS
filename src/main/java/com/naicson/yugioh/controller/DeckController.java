package com.naicson.yugioh.controller;

import java.util.List;

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
import com.naicson.yugioh.dto.KonamiDeck;
import com.naicson.yugioh.dto.KonamiDeckWithDTO;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.resttemplates.DeckRestTemplate;
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
	
	@PostMapping("/new-deck")
	public ResponseEntity<KonamiDeck> registerNewDeck(@RequestBody KonamiDeck kDeck, @RequestHeader("Authorization") String token){
		KonamiDeck createdKonamiDeck = deckService.createNewKonamiDeckWithCards(kDeck, token);
		
		this.rabbitService.sendMessageAsJson("DECK", createdKonamiDeck);
		
		return new ResponseEntity<KonamiDeck>(createdKonamiDeck, HttpStatus.OK);
	}
	
	
	@GetMapping("/api")
	public List<RelDeckCards> consultingAPI(@RequestParam("setName") String setName){
		List<RelDeckCards> retorno = apiService.consultCardsOfADeckInYuGiOhAPI(setName);
		
		return retorno;
	}
	
	@GetMapping("/test")
	public ResponseEntity<KonamiDeck> getKonamiDeck(@RequestParam Long id, 
		@RequestParam String source, @RequestHeader("Authorization") String token){
		
		DeckRestTemplate template = new DeckRestTemplate();
		
		ResponseEntity<KonamiDeck> entity = template.getKonamiDeck(id, source, token);
		
		return entity;
		
	}
	
}
