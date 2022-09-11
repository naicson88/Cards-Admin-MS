package com.naicson.yugioh.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate template;
	
	//Send message for RabbitMQ
	public void sendMessageAsJson(String queueName, Object message) {	
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			String json = mapper.writeValueAsString(message);
			this.template.convertAndSend(queueName, json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String queueName, Object message) {	
			this.template.convertAndSend(queueName, message);

	}
	
	
}
