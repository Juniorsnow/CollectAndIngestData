package com.br.eder.ingestion_service.service;

import com.br.eder.ingestion_service.model.OrderInfo;
import com.br.eder.ingestion_service.repository.OrderInfoRepository;
import com.br.eder.ingestion_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderInfoRepository orderInfoRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderInfoRepository orderInfoRepository) {
        this.orderRepository = orderRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    public List<OrderInfo> getAllOrders() {
        return this.orderRepository.findAllBy();

    }

    public List<OrderInfo> getALlOrdersH2(){
        return this.orderInfoRepository.findAllRegisters();
    }


}
