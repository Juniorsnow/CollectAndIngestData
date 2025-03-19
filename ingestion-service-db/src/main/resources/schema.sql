CREATE TABLE order_info
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(255) NOT NULL,
    purchase_item VARCHAR(255),
    purchase_date TIMESTAMP,
    price_total DOUBLE NOT NULL,
    price_unit DOUBLE NOT NULL,
    source        varchar(255) NOT NULL
);
