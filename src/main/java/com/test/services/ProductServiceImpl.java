package com.test.services;
import com.test.database.Database;
import com.test.domain.product.Product;
import com.test.domain.sql.ProductSQL;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    public Response createProduct(Product product) throws SQLException {
        Connection connection = null;
        try {
            connection = Database.getConnection();
            System.out.println("before insert...");

            String sql = ProductSQL.CREATE_PRODUCT;
            System.out.println("after insert...");

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductPrice());
            statement.setInt(3, product.getCategoryId());
            statement.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return Response.status(201).build();
    }

    public Response deleteProduct(int productId) throws SQLException {
        Connection connection = null;

        try {
            System.out.println("Starting the delete process...");
            connection = Database.getConnection();

            String query = ProductSQL.DELETE_PRODUCT;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            int deletedProduct = statement.executeUpdate();

            if (deletedProduct == 0) {
                return Response.status(404).entity("product not found").build();
            }

            return Response.status(200).entity("product successfully deleted").build();

        } catch (SQLException e) {
            throw e;

        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public List<Product> getAllProducts() throws SQLException {
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


            return products;

        } catch (SQLException e) {
            throw e;

        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        }
    }


    public Response updateProduct(int id, Product product) throws SQLException {
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String query = ProductSQL.UPDATE_PRODUCT;

            System.out.println("before statement");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getProductPrice());
            statement.setInt(3, id);

            ResultSet updatedProduct = statement.executeQuery(query);
            return Response.status(Response.Status.OK).entity(updatedProduct).build();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    public Response getProductById(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String query = ProductSQL.GET_PRODUCT_BY_ID;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet);
                products.add(product);
            }


            return Response.status(Response.Status.OK)
                    .entity(products)
                    .build();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        }
    }

    public Response getProductsByCategory(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        System.out.println("im inside try");

        try {
            System.out.println("im inside try");
            connection = Database.getConnection();
            String query = ProductSQL.GET_PRODUCT_BY_CATEGORY;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet);
                products.add(product);
            }

            return Response.status(Response.Status.OK)
                    .entity(products)
                    .build();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

