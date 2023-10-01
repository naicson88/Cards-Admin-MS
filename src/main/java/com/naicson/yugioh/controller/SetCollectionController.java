package com.naicson.yugioh.controller;

import cardscommons.dto.AssociationDTO;
import cardscommons.dto.SetCollectionDTO;
import com.naicson.yugioh.configs.RabbitMQConstantes;
import com.naicson.yugioh.service.RabbitMQService;
import com.naicson.yugioh.service.setCollection.SetCollectionServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({ "v1/admin/set-collection" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class SetCollectionController {
	
	@Autowired
	SetCollectionServiceImpl collectionService;
	
	@Autowired
	private RabbitMQService rabbitService;
	
	Logger logger = LoggerFactory.getLogger(SetCollectionController.class);
	
	@PostMapping("/new-collection")
	public ResponseEntity<SetCollectionDTO> newSetCollection(@Valid @RequestBody SetCollectionDTO collection,
															 @RequestHeader("Authorization") String token) {
		
		logger.info("Starting creating new SetCollection...");

		this.rabbitService.sendMessageAsJson(RabbitMQConstantes.SETCOLLECTION_QUEUE, collection);
		
		logger.info("Message sent successfully to SETCOLLECTION Queue");
		
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	@PostMapping("/new-association")
	public ResponseEntity<String> newAssociation(@Valid @RequestBody AssociationDTO dto, @RequestHeader("Authorization") String token){
		logger.info("Starting creating new Association...");
		
		collectionService.newAssociation(dto, token);
		
		logger.info("Association sent successfully!");
		
		return new ResponseEntity<>(JSONObject.quote("Association sent successfully!"), HttpStatus.OK);
	}
}
