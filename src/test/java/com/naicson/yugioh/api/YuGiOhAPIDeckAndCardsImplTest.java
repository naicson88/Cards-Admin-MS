package com.naicson.yugioh.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.naicson.yugioh.entity.RelDeckCards;
import com.naicson.yugioh.mocks.RelDeckCardsMock;
import com.naicson.yugioh.resttemplates.YuGiOhAPIDeckAndCardsRestTemplate;
import com.naicson.yugioh.service.yugiohAPI.YuGiOhAPIDeckAndCardsImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
 public class YuGiOhAPIDeckAndCardsImplTest {
	
	@Spy
	@InjectMocks
	YuGiOhAPIDeckAndCardsImpl service;
	@Mock
	YuGiOhAPIDeckAndCardsRestTemplate restTemplate;
	
	List<RelDeckCards> listRel;
	
	private final String json = "{\"data\":[{\"id\":21044178,\"name\":\"AbyssDweller\",\"type\":\"XYZMonster\",\"desc\":\"2Level4monsters\\nWhilethiscardhasamaterialattachedthatwasoriginallyWATER,allWATERmonstersyoucontrolgain500ATK.Onceperturn(QuickEffect):Youcandetach1materialfromthiscard;youropponentcannotactivateanycardeffectsintheirGYthisturn.\",\"atk\":1700,\"def\":1400,\"level\":4,\"race\":\"SeaSerpent\",\"attribute\":\"WATER\",\"card_sets\":[{\"set_name\":\"Abyss Rising\",\"set_code\":\"ABYR-EN084\",\"set_rarity\":\"SuperRare\",\"set_rarity_code\":\"(SR)\",\"set_price\":\"2.98\"}]}]}";
	
	@BeforeEach
	private  void setup(){
	    MockitoAnnotations.openMocks(this); 
	    listRel = List.of(RelDeckCardsMock.relDeckCards(), RelDeckCardsMock.relDeckCards());
	}
	
//	@Test
//	public void consultCardsOfADeckInYuGiOhAPI() {
//		String setName = "Abyss Rising";
//		
//		
//		Mockito.when(restTemplate.getCardsFromSetInYuGiOhAPI(setName)).thenReturn(json);
//		
//		List<RelDeckCards> list = service.consultCardsOfADeckInYuGiOhAPI(setName);
//		
//		assertNotNull(list);
//		assertEquals(1, list.size());
//		assertEquals("ABYR-EN084", list.get(0).getCardSetCode());		
//	}
	
}


