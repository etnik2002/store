package com.test.resources;
import com.test.resources.OrderResource;
import com.test.Store.Store;
import com.test.services.OrderServiceImpl;
import com.test.responses.Responses;
import java.sql.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/orders")
public class OrderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() throws SQLException {
        Responses httpResponse = new Responses();
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


    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersWithSpecificProduct(@PathParam("id") int id) {
        Responses httpResponse = new Responses();
        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            return orderService.getOrdersWithSpecificProduct(id);

        } catch (Exception e) {
            return  httpResponse.error(e);

        }
    }


}
