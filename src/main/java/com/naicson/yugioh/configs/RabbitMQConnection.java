package com.naicson.yugioh.configs;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;

import org.springframework.amqp.core.Queue;

@Component
public class RabbitMQConnection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	private final AmqpAdmin amqAdmin;
	
	public RabbitMQConnection(AmqpAdmin admin) {
		this.amqAdmin = admin;
	}
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}

	@PostConstruct
	public void add() {
		this.createDeckQueue();
		this.createSetCollectionQueue();
		this.createDeckCollectionQueue();
		this.createCardQueue();
		this.createSetPriceQueue();
//		this.createCardPriceQueue();
	}

	private void declareCreation(Queue deckQueue, DirectExchange change, Binding binding){
		this.amqAdmin.declareQueue(deckQueue);
		this.amqAdmin.declareExchange(change);
		this.amqAdmin.declareBinding(binding);
	}

	private void createDeckQueue() {
		Queue deckQueue = this.queue(RabbitMQConstantes.DECK_QUEUE);
		DirectExchange change = this.directExchange();
		Binding deckBinding = this.relationship(deckQueue, change);
		
		this.declareCreation(deckQueue, change, deckBinding);
	}
	
	private void createSetCollectionQueue() {
		Queue setCollectionQueue = this.queue(RabbitMQConstantes.SETCOLLECTION_QUEUE);
		DirectExchange change = this.directExchange();
		
		Binding collectionBinding = this.relationship(setCollectionQueue, change);
		this.declareCreation(setCollectionQueue, change, collectionBinding);
	}
	
	private void createDeckCollectionQueue() {
		Queue deckCollectionQueue = this.queue(RabbitMQConstantes.DECK_COLLECTION_QUEUE);
		DirectExchange change = this.directExchange();
		
		Binding collectionBinding = this.relationship(deckCollectionQueue, change);

		this.declareCreation(deckCollectionQueue, change, collectionBinding);
	}
	
	private void createCardQueue() {
		Queue cardQueue = this.queue(RabbitMQConstantes.CARD_QUEUE);
		DirectExchange change = this.directExchange();
		
		Binding cardBinding = this.relationship(cardQueue, change);

		this.declareCreation(cardQueue, change, cardBinding);
	}
	
	private void createSetPriceQueue() {
		Queue setPriceQueue = this.queue(RabbitMQConstantes.SET_PRICE_QUEUE);
		DirectExchange change = this.directExchange();	
		Binding setPriceBinding = this.relationship(setPriceQueue, change);

		this.declareCreation(setPriceQueue, change, setPriceBinding);
	}
	
//	private void createCardPriceQueue() {
//		Queue cardPriceQueue = this.queue(RabbitMQConstantes.CARD_PRICE_QUEUE);
//		DirectExchange change = this.directExchange();	
//		Binding cardPriceBinding = this.relationship(cardPriceQueue, change);
//
//		this.amqAdmin.declareQueue(cardPriceQueue);
//		
//		this.amqAdmin.declareExchange(change);
//		
//		this.amqAdmin.declareBinding(cardPriceBinding);	
//	}
	
	
}
