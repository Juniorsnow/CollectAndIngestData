package com.br.eder.ingestion_service.serializer;


import com.br.eder.ingestion_service.model.OrderInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderSerializer {

    private final ObjectMapper objectMapper;
    private final Logger LOG = LoggerFactory.getLogger(OrderSerializer.class);


    @Autowired
    public OrderSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public byte[] orderToByte(List<OrderInfo> orderInfo){
        try{
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsBytes(orderInfo);
        }catch (Exception e){
            LOG.debug("error with serialize to byte[] generates a follow error", e);
            throw new RuntimeException(e);
        }
    }

}
