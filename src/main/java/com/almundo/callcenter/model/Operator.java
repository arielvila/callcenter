package com.almundo.callcenter.model;

public class Operator implements Employee {
    private final int priority = 1;
    private String name;

    public Operator(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee employee) {
        if (this.priority < employee.getPriority()) {
            return -1;
        }

        return 1;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return this.name;
    }
}