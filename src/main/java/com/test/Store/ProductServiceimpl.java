//package com.test.Store;
//import com.test.database.Database;
//import com.test.domain.category.Category;
//import com.test.domain.product.Product;
//
//import javax.ws.rs.core.Response;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductServiceimpl {
//
//    public Response createProduct(Store store) throws SQLException {
//        Connection connection = null;
//        try {
//
//            System.out.println("im here...");
//            connection = Database.getConnection();
//            System.out.println("before insert...");
//
//            String sql = "INSERT INTO product ( productName, productPrice, productCategory) VALUES (?, ?, ?)";
//            System.out.println("after insert...");
//
//            Category productCategory = new Category();
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, store.getProductName());
//            statement.setInt(2, store.getProductPrice());
//            statement.setInt(3, store.setProductCategory(productCategory.getCategoryId()));
//            statement.execute();
//
//        } catch (Exception e) {
//            System.out.println("error happened" + e);
//            return Response.status(500).entity("error -> " + e).build();
//        } finally {
//            if(connection != null) {
//                connection.close();
//            }
//        }
//        return Response.status(201).build();
//    }
//
//    public Response deleteProduct(int productId) throws SQLException {
//        Connection connection = null;
//        try {
//            System.out.println("Starting the delete process...");
//            connection = Database.getConnection();
//
//            String deleteQuery = "DELETE FROM product WHERE id = ?";
//            PreparedStatement statement = connection.prepareStatement(deleteQuery);
//            statement.setInt(1, productId);
//            int deletedProduct = statement.executeUpdate();
//
//            if (deletedProduct == 0) {
//                return Response.status(404).entity("product not found").build();
//            }
//
//            return Response.status(200).entity("product successfully deleted").build();
//
//        } catch (SQLException e) {
//            System.err.println("error deleting product: " + e.getMessage());
//            return Response.status(500).entity("error deleting product").build();
//
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public Response getAllProducts() {
//        List<Store> stores = new ArrayList<>();
//        Store store = new Store();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_hotelkey", "root", "");
//            String query = ProductQuerys.GET_PRODUCTS;
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                store.setProduct_id(resultSet.getInt("id"));
//                store.setProductName(resultSet.getString("productName"));
//                store.setProductCategory(resultSet.getInt("productCategory"));
//                store.setProductPrice(resultSet.getInt("productPrice"));
//                stores.add(store);
//            }
//
//            connection.close();
//
//            return Response.status(Response.Status.OK)
//                    .entity(stores)
//                    .build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error retrieving data from com.test.database")
//                    .build();
//        }
//    }
//
//    public boolean updateProduct(int id, Store product) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_hotelkey", "root", "");
//            String query = ProductQuerys.UPDATE_PRODUCT;
//
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, product.getProductName());
//            statement.setDouble(2, product.getProductPrice());
//            statement.setInt(3, id);
//
//            int rowsUpdated = statement.executeUpdate();
//
//            if (rowsUpdated > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//
//    public Response getProductById(int id) {
//        List<Product> products = new ArrayList<>();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_hotelkey", "root", "");
//            String query = ProductQuerys.GET_PRODUCT_BY_ID;
//
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                Product product = new Product();
//                product.setId(resultSet.getInt("id"));
//                product.setProductName(resultSet.getString("productName"));
//                product.setCategoryId(resultSet.getInt("categoryId"));
//                product.setProductPrice(resultSet.getInt("productPrice"));
//                products.add(product);
//            }
//
//            connection.close();
//
//            return Response.status(Response.Status.OK)
//                    .entity(products)
//                    .build();
//
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error retrieving data from com.test.database")
//                    .build();
//        }
//    }
//
////    public Response getProductsByCategory(int categoryId) {
////        List<Product> products = new ArrayList<>();
////        Connection connection = null;
////
////        try{
////            connection = Database.getConnection();
////            String query = ProductQuerys.GET_PRODUCT_BY_ID;
////
////            PreparedStatement preparedStatement = connection.prepareStatement(query);
////            preparedStatement.setInt(1, categoryId);
////            ResultSet resultSet = preparedStatement.executeQuery();
////
////            while (resultSet.next()) {
////                Product product = new Product();
////                product.setId(resultSet.getInt("id"));
////                product.setProductName(resultSet.getString("productName"));
////                product.setCategoryId(resultSet.getInt("categoryId"));
////                product.setProductPrice(resultSet.getInt("productPrice"));
////                products.add(product);
////            }
////
////            connection.close();
////
////            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
////                    .entity(products)
////                    .build();
////
////        }catch (Exception e) {
////            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
////                    .entity("Error retrieving data from com.test.database")
////                    .build();
////        }
////    }
//
//
//}
