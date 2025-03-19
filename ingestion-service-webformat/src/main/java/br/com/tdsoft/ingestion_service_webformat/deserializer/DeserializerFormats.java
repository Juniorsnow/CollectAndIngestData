package br.com.tdsoft.ingestion_service_webformat.deserializer;


import br.com.tdsoft.ingestion_service_webformat.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DeserializerFormats {

    ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOG = LoggerFactory.getLogger(DeserializerFormats.class);

    public byte[] simpleFileReader(){
        try{
            User userJson = objectMapper.readValue(new File("D:\\databasepipeline\\ingestion-service-webformat\\src\\main\\resources\\jsonExample.json"), User.class);
            return objectMapper.writeValueAsBytes(userJson);
        }catch(Exception e){
            LOG.debug("An error occurred while generates a byte[]");
            throw new RuntimeException(e);
        }
    }

}
