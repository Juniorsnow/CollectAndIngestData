package br.com.tdsoft.ingestion_service_webformat.kafka;

import br.com.tdsoft.ingestion_service_webformat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessages {

    @Value("${kafka.topic.name}")
    String topic;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final UserService userService;
    private final Logger LOG = LoggerFactory.getLogger(KafkaMessages.class);

    @Autowired
    public KafkaMessages(KafkaTemplate<String, byte[]> kafkaTemplate, UserService userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }

    //topic data-file
    public void sendDataToKafka() {
        try {
            kafkaTemplate.send(topic, this.userService.getDeserializerFormats());
        } catch (Exception e) {
            LOG.debug("error with serialize to byte[] generates a follow error", e);
            throw new RuntimeException(e);
        }
    }

}
