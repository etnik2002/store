package com.test.resources;

import javax.ws.rs.*;


@Path("/")
public class ApplicationResource {

    @GET
    @Path("/")
    public static void main () {
        System.out.println("com.test.Store api");


    }

}

