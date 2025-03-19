package br.com.eder.DataProcessor.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfigurationBeans {

    @Bean
    public CamelContext camelContext(){
        return new DefaultCamelContext();
    }
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
