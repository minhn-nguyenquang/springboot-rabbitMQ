package com.example.demo.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.request.TransactionFileRequest;

@Service
public class RabbitMqSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	private String routingkey;

	public void sendFile(TransactionFileRequest transactionFileRequest) {
		rabbitTemplate.convertAndSend(exchange, routingkey, transactionFileRequest);
	}
}
