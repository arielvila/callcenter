package com.almundo.callcenter.model;

import java.util.Random;

public class Call {
    public final static int MIN_CALL_TIME = 5000;
    public final static int MAX_CALL_TIME = 10000;

    private int duration;
    private Employee assignedEmployee;

    public Call() {
        Random random = new Random();
        this.duration = random.nextInt(MAX_CALL_TIME-MIN_CALL_TIME) + MIN_CALL_TIME;
    }

    public int getDuration() {
        return this.duration;
    }

    public Employee getAssignedEmployee() {
        return this.assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Employee hangUp() {
        Employee employee = this.assignedEmployee;
        this.assignedEmployee = null;

        return employee;
    }
}
