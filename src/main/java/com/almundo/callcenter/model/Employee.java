package com.almundo.callcenter.model;

public interface Employee extends Comparable<Employee> {
    int getPriority();
    String getName();
}