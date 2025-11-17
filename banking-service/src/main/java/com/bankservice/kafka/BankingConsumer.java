package com.bankservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

@Component
public class BankingConsumer {

	
	 @KafkaListener(topics = "${app.kafka.topic.notifications}", groupId = "${spring.kafka.consumer.group-id}")
	    public void listen(String message) {
	        System.out.println("Kafka notification received: " + message);
	        // you can extend this to call notification service (email/SMS) etc.
	    }
}
