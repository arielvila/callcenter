package com.almundo.callcenter.model;

public abstract class Employee implements Comparable<Employee> {
    private String name;
    private int priority = 0;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee employee) {
        if (this.getPriority() < employee.getPriority()) {
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