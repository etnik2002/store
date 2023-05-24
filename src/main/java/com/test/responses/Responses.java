package com.test.responses;

import javax.ws.rs.core.Response;

public class Responses {
    public  <T> Response error(T message) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
    public <T> Response success(T message) {
        return Response.status(Response.Status.OK).entity(message).build();
    }
    public <T> Response created(T object) {
        return Response.status(Response.Status.CREATED).entity(object).build();
    }
    public <T> Response updated(T message) {
        return Response.status(Response.Status.OK).entity(message).build();
    }

    public <T> Response notFound(T message) {
        return Response.status(Response.Status.NOT_FOUND).entity(message).build();
    }
    public <T> Response gsonToJson(T object) {
        return Response.status(Response.Status.OK).entity(object).build();
    }

}
