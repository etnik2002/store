package com.test.domain.sql;

public class CategorySQL {

    public static final String GET_ALL_CATEGORIES = "SELECT * FROM category";

    public static final String CREATE_CATEGORY = "INSERT INTO category (categoryName, categoryId) VALUES (?, ?)";

    public static final String GET_PRODUCTS_FROM_CATEGORY = "SELECT * FROM product p INNER JOIN category c on p.categoryId = c.categoryId where p.categoryId = ?";

}
