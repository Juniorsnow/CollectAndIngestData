package com.br.eder.ingestion_service.repository;

import com.br.eder.ingestion_service.model.OrderInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderInfoRepository {


    private final JdbcTemplate jdbcTemplate;

    public OrderInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<OrderInfo> findAllRegisters() {
        String sql = "SELECT * FROM order_info";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(rs.getLong("id"));
            orderInfo.setOwnerName(rs.getString("owner_name"));
            orderInfo.setPurchaseItem(rs.getString("purchase_item"));
            orderInfo.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
            orderInfo.setPriceUnit(rs.getDouble("price_unit"));
            orderInfo.setPriceTotal(rs.getDouble("price_total"));
            orderInfo.setSource(rs.getString("source"));

            return orderInfo;
        });
    }


}

