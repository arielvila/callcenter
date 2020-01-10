package com.almundo.callcenter.model;

public class Operator extends Employee {
    private int priority = 1;

    public Operator(String name) {
        super(name);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

}