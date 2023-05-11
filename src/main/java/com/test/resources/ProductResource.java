package com.test.resources;

import com.google.gson.Gson;
import com.test.Store.Store;

import java.sql.*;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.test.domain.product.Product;
import com.test.services.ProductServiceImpl;

@Path("/products")
public class ProductResource {

    @POST
    public Response createProduct(Product product) {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            Response createdProduct = productService.createProduct(product);
            return createdProduct;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            Response deletedProduct = productService.deleteProduct(id);
            return deletedProduct;
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            Response product = productService.getProductById(id);
            return product;
        } catch (Exception e) {
            System.out.println("Error getting product by ID: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() throws SQLException {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            List<Product> products = productService.getAllProducts();
            return Response.status(Response.Status.OK).entity(new Gson().toJson(products)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving data from com.test.database")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product) throws SQLException {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            System.out.println("im here...");
            Response updatedProduct = productService.updateProduct(id, product);

            if (updatedProduct.getLength() < 1) {
                return Response.status(404).entity("Product with ID " + id + " not found.").build();
            }

            return Response.status(200).entity(updatedProduct).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByCategory(@PathParam("categoryId") int categoryId) throws SQLException {
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            return productService.getProductsByCategory(categoryId);

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving data from com.test.database")
                    .build();
        }
    }


}
