package br.com.eder.DataProcessor.camel.fromcontrollerapplicationproducer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RouteApiDataDb extends RouteBuilder {

    public void configure() throws Exception {
        from("direct:http-external-db")
                .routeId("ingestion-service-db-trigger")
                .autoStartup(false)
                .log("Calling external API")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8080/send")
                .log("Response from external API: ${header.CamelHttpResponseCode}");

        from("kafka:data-db?brokers=localhost:9092&groupId=data-processor-group")
                .routeId("process-kafka-message")
                .autoStartup(false)
                .process(exchange -> {
                    try{
                        byte[] kafkaMessage = exchange.getIn().getBody(byte[].class);
                        ObjectMapper objectMapper = new ObjectMapper();

                        List<Map<String, Object>> items = objectMapper.readValue(kafkaMessage,
                                new TypeReference<List<Map<String, Object>>>() {
                                });

                        List<Map<String, Object>> filteredItems = items.stream()
                                .filter(item -> ((Double) item.get("priceTotal")) > 4500)
                                .toList();
                        exchange.getIn().setBody(filteredItems);

                        
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("something went wrong with value");
                    }

                })
                .log("Filtered message: ${body}");
    }
}
