package com.naicson.yugioh.service.setCollection;

import cardscommons.dto.AssociationDTO;
import com.naicson.yugioh.resttemplates.SetCollectionRestTemplate;
import com.naicson.yugioh.service.DeckServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SetCollectionServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(SetCollectionServiceImpl.class);
	
	@Autowired
	DeckServiceImpl deckService;
	
	@Autowired
	SetCollectionRestTemplate setTemplate;

	public AssociationDTO newAssociation(@Valid AssociationDTO dto, String token) {
		
		if(token == null || token.isBlank())
			throw new IllegalArgumentException("Invalid Token informed! " + token);
		
		ResponseEntity<String> respEntity = setTemplate.sendNewAssociation(dto, token);
		
		if( respEntity.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("It was not possible register a new Association " + respEntity.getBody());
		
		return dto;	
	}
	

}
