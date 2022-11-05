package com.naicson.yugioh.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.naicson.yugioh.dto.AddNewCardToDeckDTO;
import com.naicson.yugioh.dto.CardYuGiOhAPI;
import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.mocks.AddNewCardToDeckDTOMock;
import com.naicson.yugioh.mocks.CardYuGiOhAPIMock;
import com.naicson.yugioh.service.CardServiceDetailImpl;
import com.naicson.yugioh.service.CardServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {
	
	@Spy
	@InjectMocks
	CardServiceImpl cardService;
	
	@Mock
	CardServiceDetailImpl cardServiceImpl;
	
	@BeforeEach
	public void setup(){
	    MockitoAnnotations.openMocks(this); 
	}
	
	@Test
	public void addNewCardToExistingDeck() {
		
		//Given
		Long[] cardsNotRegistered = {123456l, 654321L};
		String token = "Token Test";
		AddNewCardToDeckDTO dto = AddNewCardToDeckDTOMock.newCard();
		List<CardYuGiOhAPI> list = List.of(CardYuGiOhAPIMock.createCardYuGiOh(), CardYuGiOhAPIMock.createCardYuGiOh());
		//When	
		Mockito.when(cardServiceImpl.verifyCardsNotRegistered(anyList(), anyString())).thenReturn(cardsNotRegistered);
		Mockito.when(cardServiceImpl.getCardsToBeRegistered(anyList())).thenReturn(list);		
		AddNewCardToDeckDTO card = cardService.addNewCardToDeck(dto, token);	
		//Then
		assertNotNull(card);
		assertEquals(card.getCardsToBeRegistered().size(), list.size());
		assertTrue(card.getCardsToBeRegistered().get(0).getAtk().equals(1500));
		
		
	}
}
