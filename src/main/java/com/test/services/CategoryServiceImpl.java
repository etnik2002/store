package com.test.services;

import com.test.database.Database;
import com.test.domain.category.Category;
import com.test.domain.product.Product;
import com.test.domain.sql.CategorySQL;
import com.test.responses.Responses;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl {
    Responses httpResponse = new Responses();

    public Response createCategory(Category category) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = Database.getConnection();
            ps = connection.prepareStatement(CategorySQL.CREATE_CATEGORY);
            ps.setString(1, category.getCategoryName());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error happened " + e);
            return httpResponse.error(e);

        } finally {
            if (connection != null) {
                connection.close();
            }
            if(ps != null) {
                ps.close();
            }
        }
        return httpResponse.created("product created successfully");
    }


    public Response getAllCategories() throws Exception {
        Connection connection = null;

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


    public Category getCategoryById(int id) throws Exception {
        Connection connection = null;
        Category category = null;
        List<Product> products = new ArrayList<>();

        try {
            connection = Database.getConnection();
            String query = CategorySQL.GET_CATEGORY_BY_ID;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                category = new Category(resultSet);
                products.add(new Product(resultSet));
                category.setProducts(products);
            }

            return category;
        } catch (SQLException e) {
            System.out.println("Error in getting Category by id");
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

}