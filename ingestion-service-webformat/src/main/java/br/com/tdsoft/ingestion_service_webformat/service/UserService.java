package br.com.tdsoft.ingestion_service_webformat.service;

import br.com.tdsoft.ingestion_service_webformat.deserializer.DeserializerFormats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final DeserializerFormats deserializerFormats;
    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(DeserializerFormats deserializerFormats) {
        this.deserializerFormats = deserializerFormats;
    }

    public byte[] getDeserializerFormats() {
        try {
            byte[] msg = deserializerFormats.simpleFileReader();

            if (msg == null) {
                LOG.debug("Message to be sent cannot be null or empty");
                throw new IllegalArgumentException("Message to be sent cannot be null or empty");
            }
            return msg;
        } catch (Exception e) {
            LOG.debug("Failed to send message to Kafka topic:");
            throw new RuntimeException(e);
        }

    }
}
