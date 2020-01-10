package com.almundo.callcenter;

import com.almundo.callcenter.model.*;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class EmployeeTest
{
    @Test
    public void firstEmployeeIsAnOperator() {
        TreeSet<Employee> treeSet = new TreeSet();
        treeSet.add(new Supervisor("Supervisor 1"));
        treeSet.add(new Director("Director 1"));
        treeSet.add(new Operator("Operator 1"));
        treeSet.add(new Operator("Operator 2"));
        treeSet.add(new Supervisor("Supervisor 2"));

        Employee firstEmployee = treeSet.pollFirst();

        assertEquals(firstEmployee.getClass(), Operator.class);
    }

    @Test
    public void lastEmployeeIsADirector() {
        TreeSet<Employee> treeSet = new TreeSet();
        treeSet.add(new Supervisor("Supervisor 1"));
        treeSet.add(new Director("Director 1"));
        treeSet.add(new Operator("Operator 1"));
        treeSet.add(new Operator("Operator 2"));
        treeSet.add(new Supervisor("Supervisor 2"));

        Employee lastEmployee = treeSet.pollLast();

        assertEquals(lastEmployee.getClass(), Director.class);
    }

    @Test
    public void secondEmployeeIsASupervisor() {
        TreeSet<Employee> treeSet = new TreeSet();
        treeSet.add(new Supervisor("Supervisor 1"));
        treeSet.add(new Director("Director 1"));
        treeSet.add(new Operator("Operator 1"));
        treeSet.add(new Supervisor("Supervisor 2"));

        Employee firstEmployee = treeSet.pollFirst();
        Employee secondEmployee = treeSet.pollFirst();

        assertEquals(secondEmployee.getClass(), Supervisor.class);
    }
}
