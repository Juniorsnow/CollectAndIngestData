package com.br.eder.ingestion_service.controller;

import com.br.eder.ingestion_service.kafka.KafkaMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final KafkaMessages kafkaMessages;


    @Autowired
    public OrderController(KafkaMessages kafkaMessages) {
        this.kafkaMessages = kafkaMessages;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOrder() {
        try {
            kafkaMessages.sendDataToKafka();
            return ResponseEntity.ok("Order list sent with success to kafka topic: " + kafkaMessages.getTopic());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error with send to kafka: " + e.getMessage());
        }

    }


}
