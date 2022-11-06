package com.naicson.yugioh.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;

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

import com.naicson.yugioh.dto.CardYuGiOhAPI;
import com.naicson.yugioh.mocks.CardYuGiOhAPIMock;
import com.naicson.yugioh.service.CardServiceDetailImpl;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPICardsImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) // Usado com Junit5 ao inves do RunWith
public class CardServiceDetailImplTest {
	
	@Mock
	YuGiOhAPICardsImpl apiCards;
	
	@InjectMocks
	@Spy
	CardServiceDetailImpl cardService;
	
	@BeforeEach
	private void setup(){
	    MockitoAnnotations.openMocks(this); 
	}
	
	@Test
	public void getSuccessfullyCardsToBeRegistered() {
		List<Long> cardsNotRegistered = List.of(80060561L, 15606509L);
		
		Mockito.when(apiCards.consultCardOnYuGiOhAPI(anyLong())).thenReturn(CardYuGiOhAPIMock.createCardYuGiOh());
		
		List<CardYuGiOhAPI> cardsToBeRegistered = cardService.getCardsToBeRegistered(cardsNotRegistered);
		
		assertNotNull(cardsToBeRegistered);
		assertEquals(cardsToBeRegistered.size(), cardsNotRegistered.size());
		assertEquals(1500, cardsToBeRegistered.get(0).getAtk());
		
	}
}
