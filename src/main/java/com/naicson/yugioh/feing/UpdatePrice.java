package com.naicson.yugioh.feing;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Card-Price", url = "https://yugiohprices.com/api")
public interface UpdatePrice {
	
	@GetMapping("/set_data/{deckName}")
	Optional<String> getDeckPrice(@PathVariable String deckName); 
	
	@GetMapping("/get_card_prices/{card_name}")
	Optional<String> getCardPrice(@PathVariable String card_name); 
	
}
