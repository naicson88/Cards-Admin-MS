package com.naicson.yugioh.service.interfaces;

import com.naicson.yugioh.dto.SetCollectionDto;

public interface SetCollectionService {
	
	SetCollectionDto createNewSetCollection(SetCollectionDto collection, String token);
}
