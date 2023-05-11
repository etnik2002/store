package com.test.resources;

import com.test.domain.category.Category;
import com.test.domain.product.Product;
import com.test.services.CategoryServiceImpl;
import com.test.responses.Responses;
import java.sql.*;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/category")
public class CategoryResource {

    @POST
    public Response createCategory(Category category) throws SQLException {
        System.out.println("starting");
        Responses httpResponse = new Responses();
        try{

            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            Response createdCategory = categoryService.createCategory(category);
            return httpResponse.created(createdCategory);

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public Response getAllCategories() throws SQLException {
        Responses httpResponse = new Responses();

            try {
                CategoryServiceImpl categoryService = new CategoryServiceImpl();
                Response categories = categoryService.getAllCategories();
                return categories;

            } catch (SQLException e) {
                return httpResponse.error(e);
            }
        }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
        public Response getCategoryWithProducts(@PathParam("id") int id) throws SQLException {
        Responses httpResponse = new Responses();
        try {
            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            Response category = categoryService.getCategoryWithProducts(id);
            return category;

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

}
