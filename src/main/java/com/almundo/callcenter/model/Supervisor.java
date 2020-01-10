package com.almundo.callcenter.model;

public class Supervisor extends Employee {
    private final int priority = 2;

    public Supervisor(String name) {
        super(name);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

}
