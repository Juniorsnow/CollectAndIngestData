package com.br.eder.ingestion_service;


import com.br.eder.ingestion_service.model.OrderInfo;
import com.br.eder.ingestion_service.repository.OrderInfoRepository;
import com.br.eder.ingestion_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class H2DataTests {

    @Mock
    private OrderInfoRepository orderInfoRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testGetAllOrders() {
        List<OrderInfo> mockOrders = List.of(new OrderInfo(), new OrderInfo());
        when(orderInfoRepository.findAllRegisters()).thenReturn(mockOrders);

        List<OrderInfo> result = orderService.getALlOrdersH2();

        assertEquals(2, result.size());
        verify(orderInfoRepository, times(1)).findAllRegisters();
    }

}
