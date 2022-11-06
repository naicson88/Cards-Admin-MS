package com.naicson.yugioh.configs;

public class RabbitMQConstantes {
	
	public static final String DECK_QUEUE = "DECK";
	public static final String SETCOLLECTION_QUEUE = "SETCOLLECTION";
	public static final String DECK_COLLECTION_QUEUE = "DECK_COLLECTION";
	public static final String CARD_QUEUE = "CARD_QUEUE";
	
	private RabbitMQConstantes() {
		throw new IllegalStateException("Utility class");
	}
}
