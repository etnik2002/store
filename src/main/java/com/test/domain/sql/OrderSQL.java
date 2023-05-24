package com.test.domain.sql;

public class OrderSQL {

    public static final String PLACE_ORDER = "INSERT INTO orders (id, product_id) VALUES(?, ?)";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String GET_ORDER_BY_ID = "SELECT o.*, p.* FROM orders o LEFT JOIN product p ON o.product_id = p.id WHERE o.id = ?";

}
