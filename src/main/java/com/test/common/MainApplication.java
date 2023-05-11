package com.test.common;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class MainApplication extends javax.ws.rs.core.Application {

    public MainApplication() {
        System.out.println("Initiating app.....");

    }


    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(classes.class);
        return classes;
    }
}