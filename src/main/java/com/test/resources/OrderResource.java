package com.test.resources;
import com.google.gson.Gson;
import com.test.domain.category.Category;
import com.test.domain.order.Order;
import com.test.domain.product.Product;
import com.test.resources.OrderResource;
import com.test.services.OrderServiceImpl;
import com.test.responses.Responses;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/orders")
public class OrderResource {
    Responses httpResponse = new Responses();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(String payload) throws Exception {
        Order order = new Gson().fromJson(payload, Order.class);
        Order createdOrder = null;
        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            createdOrder = orderService.placeOrder(order);

        } catch (Exception e) {
            httpResponse.error(e);
        }
        return httpResponse.gsonToJson(createdOrder);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() throws Exception {
        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            return orderService.getAllOrders();

        } catch (SQLException e) {
            return  httpResponse.error(e);
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id) {
        Responses httpResponse = new Responses();
        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            return orderService.getOrderById(id);

        } catch (Exception e) {
            return  httpResponse.error(e);

        }
    }


}
