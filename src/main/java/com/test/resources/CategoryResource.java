package com.test.resources;

import com.google.gson.Gson;
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
    Responses httpResponse = new Responses();

    @POST
    public Response createCategory(String payload) throws Exception {
        Category category = new Gson().fromJson(payload, Category.class);

            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            categoryService.createCategory(category);
            return httpResponse.success("category created");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() throws Exception {
        try {
            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            Response categories = categoryService.getAllCategories();
            return categories;

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") int id) throws Exception {
        try {
            CategoryServiceImpl categoryService = new CategoryServiceImpl();
            Category category = categoryService.getCategoryById(id);
            if(category == null) {
                return httpResponse.notFound("Category not found!");
            }

            return httpResponse.gsonToJson(category);

        } catch (SQLException e) {
            return httpResponse.error(e);
        }
    }

}
