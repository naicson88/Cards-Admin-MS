package com.naicson.yugioh.service.setCollection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naicson.yugioh.dto.KonamiDeck;
import com.naicson.yugioh.dto.SetCollectionDto;
import com.naicson.yugioh.service.DeckServiceImpl;

@Service
public class SetCollectionDefaultDeck implements NewCollectionTemplate {
	
	@Autowired
	DeckServiceImpl deckService;
	
	public SetCollectionDefaultDeck(DeckServiceImpl deckServiceImpl) {
		this.deckService = deckServiceImpl;
	}

	@Override
	public SetCollectionDto createNewCollection(SetCollectionDto setCollectionDto, String token) {
		
		KonamiDeck deck = this.setCollectionToDeck(setCollectionDto);
		deck = deckService.createNewKonamiDeckWithCards(deck, token);
		setCollectionDto.setDecks(List.of(deck));
		
		return setCollectionDto;
		
	}
	
	private KonamiDeck setCollectionToDeck(SetCollectionDto collection) {
		KonamiDeck deck = new KonamiDeck();
		
		deck.setNome(collection.getName());
		deck.setImagem(collection.getImgPath());
		deck.setLancamento(collection.getReleaseDate());
		deck.setNomePortugues(collection.getPortugueseName());
		deck.setSetType(collection.getSetType());
		
		return deck;
	}

}
