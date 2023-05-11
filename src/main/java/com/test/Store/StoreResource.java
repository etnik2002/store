//package com.test.Store;
//import java.sql.*;
//import javax.ws.rs.*;
//import javax.ws.rs.core.*;
//
//@Path("/productsss")
//public class StoreResource {
//
//    @POST
//    @Path("/")
//    public Response createProduct(Store store) {
//        try {
//            ProductServiceimpl productService = new ProductServiceimpl();
//            Response response = productService.createProduct(store);
//            return response;
//        } catch(Exception e) {
//            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteProduct(@PathParam("id") int id) {
//            try {
//                ProductServiceimpl productService = new ProductServiceimpl();
//                Response response = productService.deleteProduct(id);
//                return response;
//            } catch (SQLException e) {
//                System.out.println("Error deleting product: " + e.getMessage());
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getProductById(@PathParam("id") int id) {
//        try {
//            ProductServiceimpl productService = new ProductServiceimpl();
//            Response response = productService.getProductById(id);
//            return response;
//        } catch (Exception e) {
//            System.out.println("Error getting product by ID: " + e.getMessage());
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllProducts() throws SQLException {
//        try{
//            ProductServiceimpl productService = new ProductServiceimpl();
//            Response response = productService.getAllProducts();
//            return response;
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error retrieving data from com.test.database")
//                    .build();
//        }
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateProduct(@PathParam("id") int id, Store updatedProduct) throws SQLException {
//        try {
//            ProductServiceimpl productService = new ProductServiceimpl();
//            Object allProducts = productService.getAllProducts();
//            System.out.println("im here...");
//            boolean isUpdated = productService.updateProduct(id, updatedProduct);
//
//            if (isUpdated) {
//                return Response.status(200).entity("Product with ID " + id + " has been successfully updated.").build();
//            } else {
//                return Response.status(404).entity("Product with ID " + id + " not found.").build();
//            }
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }
//
//}
