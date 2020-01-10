package com.almundo.callcenter.model;

public class Director extends Employee {
    private final int priority = 3;

    public Director(String name) {
        super(name);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

}
