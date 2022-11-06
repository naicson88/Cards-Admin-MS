package com.naicson.yugioh.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.naicson.yugioh.dto.CollectionDeck;
import com.naicson.yugioh.dto.KonamiDeck;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.mocks.CollectionDeckMock;
import com.naicson.yugioh.mocks.KonamiDeckMock;
import com.naicson.yugioh.mocks.RelDeckCardsMock;
import com.naicson.yugioh.service.CardServiceDetailImpl;
import com.naicson.yugioh.service.DeckServiceImpl;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPIDeckAndCardsImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DeckServiceImplTest {
	
	@Spy
	@InjectMocks
	DeckServiceImpl deckService;	
	@Mock
	CardServiceDetailImpl cardService;
	@Mock
	YuGiOhAPIDeckAndCardsImpl apiService;
	
	List<RelDeckCards> listRel;
	private final String TOKEN = "Token Teste";
	Long[] arr = {};
	
	@BeforeEach
	private  void setup(){
	    MockitoAnnotations.openMocks(this); 
	    listRel = List.of(RelDeckCardsMock.relDeckCards(), RelDeckCardsMock.relDeckCards());	    
	}
	
	@Test
	public void createNewKonamiDeckWithCards() {
		
		KonamiDeck kDeck = KonamiDeckMock.createKonamiDeck();
			
		Mockito.when(apiService.consultCardsOfADeckInYuGiOhAPI(kDeck.getRequestSource())).thenReturn(listRel);
		Mockito.when(cardService.verifyCardsNotRegistered(listRel, TOKEN)).thenReturn(arr);
		
		KonamiDeck deck = deckService.createNewKonamiDeckWithCards(kDeck, TOKEN);
		
		assertNotNull(deck);
		assertEquals(listRel.size(), deck.getRelDeckCards().size());
		assertTrue(deck.getRelDeckCards().get(0).getIsSpeedDuel());
		assertNull(deck.getCardsToBeRegistered());
		
	}
	
	@Test
	public void createNewCollectionDeck() {
	
		CollectionDeck cDeck = CollectionDeckMock.createCollectionDeck();
		
		Mockito.when(apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource())).thenReturn(listRel);
		Mockito.when(cardService.verifyCardsNotRegistered(listRel, TOKEN)).thenReturn(arr);
		
		CollectionDeck deck = deckService.createNewCollectionDeck(cDeck, TOKEN);
		
		assertNotNull(deck);
		assertEquals(listRel.size(), deck.getRelDeckCards().size());
		assertTrue(deck.getRelDeckCards().get(0).getIsSpeedDuel());
		assertNull(deck.getCardsToBeRegistered());	
		
	}
	
	@Test
	public void createNewCollectionDeckWithFilteredCards() {
		
		CollectionDeck cDeck = CollectionDeckMock.createCollectionDeckFiltered();
		listRel = List.of(RelDeckCardsMock.relDeckCardsFiltered("123456"),
				RelDeckCardsMock.relDeckCardsFiltered("ABCD"),
				RelDeckCardsMock.relDeckCardsFiltered(""), RelDeckCardsMock.relDeckCardsFiltered("ABCD"));
		
		Mockito.when(apiService.consultCardsOfADeckInYuGiOhAPI(cDeck.getRequestSource())).thenReturn(listRel);
		Mockito.when(cardService.verifyCardsNotRegistered(anyList(), anyString())).thenReturn(arr);
		
		CollectionDeck deck = deckService.createNewCollectionDeck(cDeck, TOKEN);
		
		assertNotNull(deck);
		assertEquals(2, deck.getRelDeckCards().size());
		assertEquals("ABCD", deck.getRelDeckCards().get(0).getCardSetCode());
		assertEquals(0, deck.getRelDeckCards()
				.stream().filter(rel -> rel.getCardSetCode().equals("123456")).count());
		
	}
}
