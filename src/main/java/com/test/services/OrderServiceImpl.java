package com.test.services;
import com.test.database.Database;
import com.test.domain.order.Order;
import com.test.domain.sql.OrderSQL;
import com.test.responses.Responses;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl {
    Responses httpResponse = new Responses();
    public Response getAllOrders() throws SQLException {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = Database.getConnection();
            String query = OrderSQL.GET_ALL_ORDERS;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setProductId(resultSet.getInt("product_id"));
                orders.add(order);
            }

            return httpResponse.success(orders);

        }catch (Exception e) {
            return httpResponse.error(e);
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    public Response getOrderById(int id) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            List<Order> orderList = new ArrayList<>();
            while(rs.next()){
                Order orderModel = new Order();
                orderModel.setId(rs.getInt("id"));
                orderModel.setProductId(rs.getInt("product_id"));
                orderList.add(orderModel);
            }

            return httpResponse.success(orderList);

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

    public Response getOrdersWithSpecificProduct(int id) throws SQLException {
        try(Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement(OrderSQL.GET_ORDERS_WITH_SPECIFIC_PRODUCT)) {
            Responses httpResponses = new Responses();

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProductId(rs.getInt("product_id"));
                orders.add(order);
            }

            return httpResponse.successGsonResponse(orders);

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

}
