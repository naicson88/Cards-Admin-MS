package com.naicson.yugioh.service.setCollection;

import com.naicson.yugioh.dto.SetCollectionDto;

public interface NewCollectionTemplate {
	
	public SetCollectionDto createNewCollection(SetCollectionDto setCollectionDto, String token);
}
