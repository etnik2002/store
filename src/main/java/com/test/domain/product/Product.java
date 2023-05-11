package com.test.domain.product;

import com.google.gson.annotations.SerializedName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    @SerializedName("name")
    String productName;
    @SerializedName("price")
    int productPrice;
    @SerializedName("category")
    int categoryId;
    @SerializedName("id")
    int id;

    public Product() {
    }

    public Product(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        productName = rs.getString("productName");
        categoryId = rs.getInt("categoryId");
        productPrice = rs.getInt("productPrice");
    }

    public int getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
