package com.test.domain.sql;

public class CategorySQL {

    public static final String GET_ALL_CATEGORIES = "SELECT * FROM category";

    public static final String CREATE_CATEGORY = "INSERT INTO category (categoryName) VALUES (?)";

    public static final String GET_CATEGORY_BY_ID = "" +
            "SELECT c.*, p.* FROM category c\n" +
            "left join product p on c.categoryId = p.categoryId\n" +
            "where c.categoryId = ?;";

}
