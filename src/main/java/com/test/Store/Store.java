package com.test.Store;

public class Store {

    String productName;
    int productPrice;
    int productCategory;
    int product_id;

    public int getProductId() {
        return product_id;
    }
    public String getProductName() {
        return productName;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public int getProductCategory() {
        return productCategory;
    }

    public int setProductPrice(int productPrice) {
        return this.productPrice = productPrice;
    }
    public int setProduct_id(int product_id) {
        return this.product_id = product_id;
    }
    public void  setProductName(String productName) {
        this.productName = productName;
    }
    public int setProductCategory(int productCategory) {
        return this.productCategory = productCategory;
    }

}
