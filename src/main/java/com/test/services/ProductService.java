package com.test.services;

import com.test.domain.product.Product;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.List;

public interface ProductService {
    Response createProduct(Product product) throws SQLException;

    Response deleteProduct(int productId) throws SQLException;

    List<Product> getAllProducts() throws SQLException;


    Response updateProduct(int id, Product product) throws SQLException;
}
