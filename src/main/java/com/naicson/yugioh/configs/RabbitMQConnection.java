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
	private AmqpAdmin amqAdmin;
	
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
	}
	
	private void createDeckQueue() {
		Queue deckQueue = this.queue(RabbitMQConstantes.DECK_QUEUE);
		
		DirectExchange change = this.directExchange();
		
		Binding deckBinding = this.relationship(deckQueue, change);
		
		//Creating RabbitMQ Queues
		this.amqAdmin.declareQueue(deckQueue);
		
		this.amqAdmin.declareExchange(change);
		
		this.amqAdmin.declareBinding(deckBinding);
	}
	
	private void createSetCollectionQueue() {
		Queue setCollectionQueue = this.queue(RabbitMQConstantes.SETCOLLECTION_QUEUE);
		DirectExchange change = this.directExchange();
		
		Binding collectionBinding = this.relationship(setCollectionQueue, change);
		
		//Creating RabbitMQ Queues
		this.amqAdmin.declareQueue(setCollectionQueue);
		
		this.amqAdmin.declareExchange(change);
		
		this.amqAdmin.declareBinding(collectionBinding);
		
	}
	
}
