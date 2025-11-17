package com.bankservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationService {

	private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;
    
    public KafkaNotificationService(KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic.notifications}") String topic) {
            this.kafkaTemplate = kafkaTemplate;
             this.topic = topic;
           }
	
    public void sendNotification(String message) {
        try {
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            // If Kafka not available, log and continue (notifications are best-effort)
            System.err.println("Kafka not available: " + e.getMessage());
        }
    }
    
}
