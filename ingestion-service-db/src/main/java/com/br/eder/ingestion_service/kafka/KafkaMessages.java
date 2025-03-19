package com.br.eder.ingestion_service.kafka;

import com.br.eder.ingestion_service.serializer.OrderSerializer;
import com.br.eder.ingestion_service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessages {

    @Value("${kafka.topic.name}")
    private String topic;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final OrderSerializer orderSerializer;
    private final OrderService orderService;
    private final Logger LOG = LoggerFactory.getLogger(KafkaMessages.class);

    @Autowired
    public KafkaMessages(KafkaTemplate<String, byte[]> kafkaTemplate,
                         OrderSerializer orderSerializer,
                         OrderService orderService) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderSerializer = orderSerializer;
        this.orderService = orderService;
    }

    public KafkaTemplate<String, byte[]> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public void sendDataToKafka(){
        try{
            byte[] order = this.orderSerializer.orderToByte(this.orderService.getALlOrdersH2());
            kafkaTemplate.send(topic, order);
        }catch (Exception e){
            LOG.debug("error message: {}", e.getMessage());
            throw new RuntimeException("Error with send data to kafka in byte array format" + e);        }
    }

    public String getTopic(){
        return topic;
    }


}
