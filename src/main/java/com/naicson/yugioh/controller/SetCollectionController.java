package com.naicson.yugioh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naicson.yugioh.dto.SetCollectionDto;
import com.naicson.yugioh.service.setCollection.SetCollectionServiceImpl;

@RestController
@RequestMapping({ "v1/admin/set-collection" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class SetCollectionController {
	
	@Autowired
	SetCollectionServiceImpl collectionService;
	
	Logger logger = LoggerFactory.getLogger(SetCollectionController.class);
	
	
	
	@PostMapping("/new-collection")
	public ResponseEntity<SetCollectionDto> newSetCollection(@RequestBody SetCollectionDto collection,
			@RequestHeader("Authorization") String token){
		
		logger.info("Starting creating new SetCollection...");
		
		collection = collectionService.createNewSetCollection(collection, token);
		
		System.out.println(collection.getDecks().get(0).getRelDeckCards().toString());
		
		return new ResponseEntity<SetCollectionDto>(collection, HttpStatus.OK);
	}
}
