package br.com.tdsoft.ingestion_service_webformat.controller;


import br.com.tdsoft.ingestion_service_webformat.kafka.KafkaMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebFormatController {

    private final KafkaMessages kafkaMessages;

    @Autowired
    public WebFormatController(KafkaMessages kafkaMessages) {
        this.kafkaMessages = kafkaMessages;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendWebFormat() {
        try{
            kafkaMessages.sendDataToKafka();
            return ResponseEntity.ok("Web format sent with success to kafka topic: ");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error with send to kafka: " + e.getMessage());
        }
    }
}
