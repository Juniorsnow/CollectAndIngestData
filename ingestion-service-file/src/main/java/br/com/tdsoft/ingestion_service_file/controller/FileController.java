package br.com.tdsoft.ingestion_service_file.controller;

import br.com.tdsoft.ingestion_service_file.kafka.KafkaMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    private final KafkaMessages kafkaMessages;

    @Autowired
    public FileController(KafkaMessages kafkaMessages) {
        this.kafkaMessages = kafkaMessages;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendExcelFile() {
        String topicName = "data-file";
        try {
            kafkaMessages.sendDataToKafka();
            return ResponseEntity.ok("Excel file sent with success to kafka topic: " + topicName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error with send to kafka: " + e.getMessage());
        }
    }
}
