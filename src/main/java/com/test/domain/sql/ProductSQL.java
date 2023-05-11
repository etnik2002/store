package com.test.domain.sql;

public class ProductSQL {
    public static final String CREATE_PRODUCT = "INSERT INTO product (productName, productPrice, productCategory) VALUES (?, ?, ?)";
    public static final String GET_PRODUCTS = "SELECT * FROM product";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    public static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, productPrice = ?, productCategory = ? WHERE id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    public static final String GET_PRODUCT_BY_CATEGORY = "SELECT * FROM product WHERE productCategory = ?";
}
