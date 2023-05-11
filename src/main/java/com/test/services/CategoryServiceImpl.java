package com.test.services;
import com.google.gson.Gson;
import com.test.database.Database;
import com.test.domain.category.Category;
import com.test.domain.product.Product;
import com.test.domain.sql.CategorySQL;
import com.test.responses.Responses;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl {

    public Response createCategory(Category category) throws SQLException {
        Connection connection = null;
        Responses httpResponse = new Responses();

        try {
            connection = Database.getConnection();
            String sql = CategorySQL.CREATE_CATEGORY;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryId());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("error happened " + e);
            return httpResponse.error(e);

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return httpResponse.created("product created successfully");
    }

    public Response getAllCategories() throws SQLException {
        Connection connection = null;
        Responses httpResponse = new Responses();

        try {
            List<Category> categories = new ArrayList<>();
            connection = Database.getConnection();
            String query = CategorySQL.GET_ALL_CATEGORIES;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Category category = new Category(resultSet);
                categories.add(category);
            }

            return httpResponse.success(categories);

        } catch (SQLException e) {
            return httpResponse.error(e);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    public Response getCategoryWithProducts(@PathParam("id") int id) throws SQLException {
        System.out.println("im inside getProductsByCategory");
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Responses httpResponse = new Responses();

        try {
            System.out.println("im inside try");
            connection = Database.getConnection();
            String query = CategorySQL.GET_PRODUCTS_FROM_CATEGORY;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(resultSet);
                categories.add(category);
                products.add(new Product(resultSet));
                category.setProducts(products);
            }

            return httpResponse.successGsonResponse(categories);
        } catch (Exception e) {
            return httpResponse.error(e);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

}