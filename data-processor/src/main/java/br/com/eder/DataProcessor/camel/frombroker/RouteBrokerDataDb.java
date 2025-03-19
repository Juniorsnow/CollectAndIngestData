package br.com.eder.DataProcessor.camel.frombroker;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteBrokerDataDb extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // in this route camel automatically interprets byte[] as JSON and converts it automatically.
        //because the real content was a converting from json to byte[]
//        from("kafka:data-db?brokers=localhost:9092&groupId=data-processor-group")
//                .log("Message received from Kafka: ${body}")
//                .log("On the topic ${headers[kafka.TOPIC]}")
//                .log("on the partition ${headers[kafka.PARTITION]}")
//                .log("with the offset ${headers[kafka.OFFSET]}")
//                .log("with the key ${headers[kafka.KEY]}");


        //just orders that cost more than 4500
//        from("kafka:data-db?brokers=localhost:9092&groupId=data-processor-group")
//                .routeId("filter-more-than-4500")
//                .split().jsonpath("$[*]")
//                .filter().jsonpath("$[?(@.priceTotal > 4500)]")
//                .log("Filtered ones: ${body}")
//                .end();




//        from("timer:trigger?repeatCount=1")
//                .routeId("kafka-consumer-http-route")
//                .log("Triggering HTTP POST request to ingestion-service-db")
//                .setHeader("CamelHttpMethod", constant("POST"))
//                .doTry()
//                .to("http://localhost:8080/send") // Envia a requisição HTTP
//                .log("HTTP request successful! Response from ingestion-service-db/send: ${body}")
//                .doCatch(Exception.class) // Captura qualquer erro que ocorrer na requisição
//                .log("HTTP request failed: ${exception.message}")
//                .end()
//                .to("direct:consumeKafka"); // Continua a rota para consumir do Kafka
//
//        from("kafka:data-db?brokers=localhost:9092&groupId=data-processor-group")
//                .routeId("data-db-consumer-route")
//                .log("Message received from Kafka: ${body}") // Loga a mensagem original do Kafka
//                .process(exchange -> {
//                    // Converte a mensagem recebida do Kafka para JSON
//                    byte[] kafkaMessage = exchange.getIn().getBody(byte[].class);
//                    String jsonMessage = new String(kafkaMessage, StandardCharsets.UTF_8);
//                    exchange.getIn().setBody(jsonMessage); // Define o corpo da mensagem como JSON
//                })
//                .log("Message after conversion to JSON: ${body}"); // Loga a mensagem convertida

    }


}
