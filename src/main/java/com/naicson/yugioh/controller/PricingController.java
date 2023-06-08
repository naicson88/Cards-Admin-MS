package com.naicson.yugioh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "v1/admin/price" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class PricingController {
	
	@GetMapping("/update-deck-price")
	public ResponseEntity<String> updateDeckPrice(@RequestParam String deckName) {
		return null;
	}
	
}
