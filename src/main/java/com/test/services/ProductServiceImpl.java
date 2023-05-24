package com.test.services;

import com.test.database.Database;
import com.test.domain.product.Product;
import com.test.domain.sql.CategorySQL;
import com.test.domain.sql.ProductSQL;
import com.test.responses.Responses;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl {
    Responses httpResponse = new Responses();

    public Product createProduct(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = Database.getConnection();
            ps = connection.prepareStatement(ProductSQL.CREATE_PRODUCT);

            ps.setInt(1, product.getId());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getProductPrice());
            ps.setInt(4, product.getCategoryId());
            ps.executeUpdate();

        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return product;
    }

    public Response deleteProduct(int productId) throws Exception {
        Connection connection = null;

        try {
            connection = Database.getConnection();

            String query = ProductSQL.DELETE_PRODUCT;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();

            return httpResponse.success("Product deleted");

        } catch (SQLException e) {
            throw e;

        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String query = ProductSQL.GET_PRODUCTS;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product(resultSet);
                products.add(product);
            }
        }

      finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        }
        return products;
    }


    public Product updateProduct(int id, Product product) throws Exception {
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String query = ProductSQL.UPDATE_PRODUCT;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getProductPrice());
            statement.setInt(3, id);

            return (Product) statement.executeQuery(query);

        } catch (Exception e) {
            httpResponse.error("error -> " + e);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return product;
    }


    public Product getProductById(int id) throws Exception {
        Product products = null;
        Connection connection = null;

        try {
            System.out.println(id);
            connection = Database.getConnection();
            String query = ProductSQL.GET_PRODUCT_BY_ID;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                products = new Product(resultSet);
            }

            return products;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        }
    }

}

