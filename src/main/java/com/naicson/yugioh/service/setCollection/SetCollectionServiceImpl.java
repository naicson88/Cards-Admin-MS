package com.naicson.yugioh.service.setCollection;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.SetCollectionDto;
import com.naicson.yugioh.service.DeckServiceImpl;
import com.naicson.yugioh.service.interfaces.SetCollectionService;

@Service
public class SetCollectionServiceImpl implements SetCollectionService{
	
	Logger logger = LoggerFactory.getLogger(SetCollectionServiceImpl.class);
	
	@Autowired
	DeckServiceImpl deckService;

	@Override
	public SetCollectionDto createNewSetCollection(SetCollectionDto collection, String token) {
		
		this.validateCollection(collection);
		
		NewCollectionTemplate template = !collection.getOnlyDefaultDeck() ? new SetCollectionWithCustomDeck() : new SetCollectionDefaultDeck(deckService);
		
		collection = template.createNewCollection(collection, token);
		
		return collection;
		
	}

	private void validateCollection(SetCollectionDto collection) {
		
		if(collection == null) {
			logger.error("Invalid SetCollection.");
			throw new IllegalArgumentException("Invalid SetCollection.");
		}
					
		if(collection.getOnlyDefaultDeck() == null) {
			logger.error("Invalid value for Only Defaul Deck");
			throw new IllegalArgumentException("Invalid value for Only Defaul Deck");			
		}
			
		if(collection.getImgPath() == null || collection.getImgPath().isBlank()) {
			logger.error("Invalid Image Path");
			throw new IllegalArgumentException("Invalid Image Path");	
		}
					
		if(collection.getName() == null || collection.getName().isBlank()) {
			logger.error("Invalid Name");
			throw new IllegalArgumentException("Invalid Name");	
		}
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1995);
		
		if(collection.getReleaseDate().before(c.getTime())) {
			logger.error("Invalid Release Date {0}",  collection.getReleaseDate().toString());
			throw new IllegalArgumentException("Invalid Release Date");
		}
				
		if(collection.getSetType() == null || collection.getSetType().isEmpty()) {
			logger.error("Invalid Set Type");
			throw new IllegalArgumentException("Invalid Set Type");
		}
			
	}
	

}
