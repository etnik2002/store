package com.test.services;

import com.test.database.Database;
import com.test.domain.order.Order;
import com.test.domain.product.Product;
import com.test.domain.sql.OrderSQL;
import com.test.responses.Responses;

import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl {
    Responses httpResponse = new Responses();

    public Order placeOrder(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = Database.getConnection();
            ps = connection.prepareStatement(OrderSQL.PLACE_ORDER);
            ps.setInt(1, order.getId());
            ps.setInt(2, order.getProductId());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return null;
    }

    public Response getAllOrders() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = Database.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(OrderSQL.GET_ALL_ORDERS);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setProductId(rs.getInt(2));
                orders.add(order);
            }
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return httpResponse.success(orders);
    }


    public Response getOrderById(int id) throws Exception {
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(OrderSQL.GET_ORDER_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            List<Order> orders = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                orders.add(order);
                products.add(new Product(rs));
                order.setProducts(products);
            } else {
                return httpResponse.notFound("order with id : " + id + " not found");
            }

            return httpResponse.gsonToJson(orders);

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

}
