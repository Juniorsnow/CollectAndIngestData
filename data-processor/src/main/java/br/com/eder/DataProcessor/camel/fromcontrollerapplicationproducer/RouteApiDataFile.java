package br.com.eder.DataProcessor.camel.fromcontrollerapplicationproducer;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteApiDataFile extends RouteBuilder {


    public void configure() throws Exception {

        from("direct:http-external-db")
                .routeId("ingestion-service-file-trigger")
                .autoStartup(false)
                .log("Calling external API")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8081/send")
                .log("Response from external API: ${header.CamelHttpResponseCode}");

        from("kafka:data-file?brokers=localhost:9092&groupId=data-processor-group")
                .routeId("process-kafka-message")
                .log("Kafka Message: ${body}")
                .autoStartup(false);


    }
}
