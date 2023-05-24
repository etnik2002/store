package com.test.domain.sql;

public class ProductSQL {
    public static final String CREATE_PRODUCT = "INSERT INTO product (id, productName, productPrice, categoryId) VALUES (?, ?, ?, ?)";
    public static final String GET_PRODUCTS = "SELECT * FROM product";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    public static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, productPrice = ?, categoryId = ? WHERE id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
}
