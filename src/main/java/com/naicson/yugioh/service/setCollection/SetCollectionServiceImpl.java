package com.naicson.yugioh.service.setCollection;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.SetCollectionDto;
import com.naicson.yugioh.service.DeckServiceImpl;
import com.naicson.yugioh.service.interfaces.SetCollectionService;
import com.naicson.yugioh.util.exceptions.ErrorMessage;

@Service
public class SetCollectionServiceImpl implements SetCollectionService{
	
	Logger logger = LoggerFactory.getLogger(SetCollectionServiceImpl.class);
	
	@Autowired
	DeckServiceImpl deckService;

	@Override
	public SetCollectionDto createNewSetCollection(SetCollectionDto collection, String token) {
		
		this.validateCollection(collection);
		
		return collection;
		
	}

	private void validateCollection(SetCollectionDto collection) {
		
		if(collection == null) 
			throw new IllegalArgumentException("Invalid SetCollection.");
							
		if(collection.getOnlyDefaultDeck() == null) 
			throw new IllegalArgumentException("Invalid value for Only Defaul Deck");			
					
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
	

}
