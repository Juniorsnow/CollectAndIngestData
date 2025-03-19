package com.br.eder.ingestion_service.repository;

import com.br.eder.ingestion_service.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findAllBy();

}
