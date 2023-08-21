package com.recruitment.model;

public class User {
    private final long id;
    private final String name;

    public User(String name) {
        this.name = name;
//TODO: atomicLong
        this.id = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
