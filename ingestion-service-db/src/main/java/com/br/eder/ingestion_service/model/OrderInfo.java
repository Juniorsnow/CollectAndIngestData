package com.br.eder.ingestion_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "purchase_item")
    private String purchaseItem;

    @Column(name = "purchase_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime purchaseDate;

    @Column(name = "price_total")
    private Double priceTotal;

    @Column(name = "price_unit")
    private Double priceUnit;

    @Column(name = "source")
    private String source;

    public OrderInfo() {}

    public OrderInfo(String ownerName, String purchaseItem, LocalDateTime purchaseDate, Double priceTotal, Double priceUnit, String source) {
        this.ownerName = ownerName;
        this.purchaseItem = purchaseItem;
        this.purchaseDate = purchaseDate;
        this.priceTotal = priceTotal;
        this.priceUnit = priceUnit;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPurchaseItem() {
        return purchaseItem;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public String getSource() {
        return source;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPurchaseItem(String purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public void setPriceUnit(Double priceUnit) {
        this.priceUnit = priceUnit;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", purchaseItem='" + purchaseItem + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", priceTotal=" + priceTotal +
                ", priceUnit=" + priceUnit +
                ", source='" + source + '\'' +
                '}';
    }
}
