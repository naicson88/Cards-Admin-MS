package com.naicson.yugioh.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "YuGiOh-API", url = "https://db.ygoprodeck.com/api/v7/cardinfo.php?cardset")
public interface YuGiOhAPI {
	
	@GetMapping()
	String getCardsFromSetInYuGiOhAPI(@RequestParam String cardset);
}
