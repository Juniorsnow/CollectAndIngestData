package br.com.eder.DataProcessor.camel.frombroker;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteBrokerDataFile extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:data-file?brokers=localhost:9092&groupId=data-processor-group")
                .log("Message received from Kafka: ${body}")
                .log("On the topic ${headers[kafka.TOPIC]}")
                .log("on the partition ${headers[kafka.PARTITION]}")
                .log("with the offset ${headers[kafka.OFFSET]}")
                .log("with the key ${headers[kafka.KEY]}");
    }
}
