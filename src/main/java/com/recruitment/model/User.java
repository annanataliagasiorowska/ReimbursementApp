package com.recruitment.model;

import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong uniqueIdGenerator = new AtomicLong(1);
    private final long id;
    private final String name;

    public User(String name) {
        this.name = name;
        this.id = uniqueIdGenerator.incrementAndGet();

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
