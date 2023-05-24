package com.test.services;

import com.test.domain.product.Product;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws Exception;

    Response deleteProduct(int productId) throws Exception;

    List<Product> getAllProducts() throws Exception;

    Product getProductById(int id) throws Exception;

    Product updateProduct(int id, Product product) throws Exception;
}
