package com.test.domain.product;

import com.google.gson.annotations.SerializedName;

import java.sql.PreparedStatement;
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

    public Product(PreparedStatement ps) throws Exception {
        ps.setInt(1, getId());
        ps.setString(2, getProductName());
        ps.setInt(3, getCategoryId());
        ps.setInt(4, getProductPrice());
    }

    public Product(ResultSet rs) throws Exception {
        id = rs.getInt("id");
        productName = rs.getString("productName");
        productPrice = rs.getInt("productPrice");
        categoryId = rs.getInt("categoryId");
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

    public int getId() {
        return id;
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
