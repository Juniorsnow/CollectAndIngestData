package br.com.eder.DataProcessor.controller;

import br.com.eder.DataProcessor.camel.service.CamelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteControllerApi {

    private final CamelService camelService;

    @Autowired
    public RouteControllerApi(CamelService camelService) {
        this.camelService = camelService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startAppExternal(@RequestParam String routeId, @RequestParam String routeIdOfKafkaRoute) throws Exception {
        if (camelService.startRoute(routeId, routeIdOfKafkaRoute)) {
            return ResponseEntity.status(HttpStatus.OK).body("Route started");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }


    }

}
