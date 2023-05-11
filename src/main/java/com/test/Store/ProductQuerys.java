package com.test.Store;

public class ProductQuerys {
        public static final String GET_PRODUCTS = "SELECT * FROM product";
        public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
        public static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, productPrice = ?, productCategory = ? WHERE id = ?";
}
