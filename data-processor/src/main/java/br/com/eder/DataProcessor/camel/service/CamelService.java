package br.com.eder.DataProcessor.camel.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamelService {


    private final ProducerTemplate producerTemplate;
    private final CamelContext camelContext;

    @Autowired
    public CamelService(ProducerTemplate producerTemplate, CamelContext camelContext) {
        this.producerTemplate = producerTemplate;
        this.camelContext = camelContext;
    }

    public boolean startRoute(String routeIdOfExternalApplication, String routeIdOfKafkaRoute) throws Exception {
        if (!camelContext.getStatus().isStarted()) {
            camelContext.start();
            if (camelContext.getRoute(routeIdOfExternalApplication) != null) {
                camelContext.getRouteController().startRoute(routeIdOfExternalApplication);
                producerTemplate.sendBody(camelContext.getRoute(routeIdOfExternalApplication).getEndpoint().getEndpointUri(), null);

                this.startConsumingKafka(routeIdOfKafkaRoute);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public void startConsumingKafka(String routeIdOfKafkaRoute) throws Exception {
        if (!camelContext.getStatus().isStarted()) {
            camelContext.start();
        }
        if (camelContext.getRoute(routeIdOfKafkaRoute) != null) {
            camelContext.getRouteController().startRoute(routeIdOfKafkaRoute);
            producerTemplate.sendBody(camelContext.getRoute(routeIdOfKafkaRoute).getEndpoint().getEndpointUri(), null);
        }

    }
}
