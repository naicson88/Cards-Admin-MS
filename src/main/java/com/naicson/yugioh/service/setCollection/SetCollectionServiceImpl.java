package com.naicson.yugioh.service.setCollection;

import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.AssociationDTO;
import com.naicson.yugioh.dto.SetCollectionDto;
import com.naicson.yugioh.resttemplates.SetCollectionRestTemplate;
import com.naicson.yugioh.service.DeckServiceImpl;
import com.naicson.yugioh.service.interfaces.SetCollectionService;

@Service
public class SetCollectionServiceImpl implements SetCollectionService{
	
	Logger logger = LoggerFactory.getLogger(SetCollectionServiceImpl.class);
	
	@Autowired
	DeckServiceImpl deckService;
	
	@Autowired
	SetCollectionRestTemplate setTemplate;

	@Override
	public SetCollectionDto createNewSetCollection(SetCollectionDto collection, String token) {
		
		this.validateCollection(collection);
		
		return collection;
		
	}

	private void validateCollection(SetCollectionDto collection) {
		
		if(collection == null) 
			throw new IllegalArgumentException("Invalid SetCollection.");
					
		if(collection.getImgPath() == null || collection.getImgPath().isBlank()) 
			throw new IllegalArgumentException("Invalid Image Path");	
							
		if(collection.getName() == null || collection.getName().isBlank()) 
			throw new IllegalArgumentException("Invalid Name");	
				
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1995);
		
		if(collection.getReleaseDate().before(c.getTime())) 
			throw new IllegalArgumentException("Invalid Release Date");
					
		if(collection.getSetType() == null || collection.getSetType().isEmpty()) 		
			throw new IllegalArgumentException("Invalid Set Type");
		
		if(collection.getIsSpeedDuel() == null)
			throw new IllegalArgumentException("Set Collection Speed Duel is invalid");			
	}

	public AssociationDTO newAssociation(@Valid AssociationDTO dto, String token) {
		
		if(token == null || token.isBlank())
			throw new IllegalArgumentException("Invalid Token informed! " + token);
		
		ResponseEntity<String> respEntity = setTemplate.sendNewAssociation(dto, token);
		
		if( respEntity.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("It was not possible register a new Association " + respEntity.getBody());
		
		return dto;	
	}
	

}
