package com.test.domain.category;

import com.test.domain.product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    String categoryName;
    Integer categoryId;
    List<Product> products;

    public Category() {
    }

    public Category(ResultSet rs) throws SQLException {
        setCategoryId(rs.getInt("categoryId"));
        setCategoryName(rs.getString("categoryName"));
        setProducts(new ArrayList<>());
    }

    public int getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryId(Integer categoryId){ this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
