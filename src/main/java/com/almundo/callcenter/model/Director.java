package com.almundo.callcenter.model;

public class Director implements Employee {
    private final int priority = 3;
    private String name;

    public Director(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee employee) {
        return 1;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return this.name;
    }
}
