package com.test.resources;
import com.google.gson.Gson;
import java.sql.*;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.test.domain.product.Product;
import com.test.responses.Responses;
import com.test.services.ProductServiceImpl;

@Path("/products")
public class ProductResource {

    Responses httpResponse = new Responses();

    @POST
    public Response createProduct(String payload) throws Exception {
        Product product = new Gson().fromJson(payload, Product.class);
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.createProduct(product);
        return httpResponse.success("created");
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) throws Exception{
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            return productService.deleteProduct(id);
        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() throws Exception {
        ProductServiceImpl productService = new ProductServiceImpl();
        List<Product> products = productService.getAllProducts();
        return httpResponse.gsonToJson(products);
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Response updateProduct(@PathParam("id") int id, Product product) throws Exception {
            ProductServiceImpl productService = new ProductServiceImpl();
            Product updatedProduct = productService.updateProduct(id, product);

            if (updatedProduct == null) {
                return httpResponse.notFound("Product with ID " + id + " not found.");
            }

            return httpResponse.success(updatedProduct);

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) throws Exception{
        try {
            ProductServiceImpl productService = new ProductServiceImpl();
            Product product = productService.getProductById(id);

            if (product == null) {
                return httpResponse.notFound("Product with ID : " + id + " not found");
            }

            return httpResponse.success(product);
        } catch (Exception e) {
            return null;
        }
    }

}
