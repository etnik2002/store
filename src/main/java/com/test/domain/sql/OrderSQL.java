package com.test.domain.sql;

public class OrderSQL {
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String GET_ORDERS_WITH_SPECIFIC_PRODUCT = "SELECT o.*, p.* FROM orders o INNER JOIN product p ON o.product_id = p.id WHERE p.id = ?";

}
