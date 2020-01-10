package com.almundo.callcenter.service;

import com.almundo.callcenter.model.*;

import java.util.*;

public class Dispatcher {
    public final static int MAX_CONCURRENT_DISPATCH = 10;

    private TreeSet<Employee> employeesList = new TreeSet();
    private ArrayDeque<Call> callsWaitingList = new ArrayDeque();
    private int currentCalls = 0;

    public synchronized void dispatchCall(Call call) {
        if (this.currentCalls < MAX_CONCURRENT_DISPATCH && this.employeesList.size() > 0) {
            Employee employee = this.employeesList.pollFirst();
            call.setAssignedEmployee(employee);
            this.dispatch(call);
            currentCalls++;
        } else {
            callsWaitingList.add(call);
        }
    }

    public synchronized void releaseEmployee(Employee employee) {
        currentCalls = currentCalls > 0 ? currentCalls - 1 : 0;
        this.employeesList.add(employee);
        if (callsWaitingList.size() > 0) {
            Call call = callsWaitingList.remove();
            this.dispatchCall(call);
        }
    }

    private void dispatch(Call call) {
        Runnable callService = new CallService(call,this);
        Thread thread = new Thread(callService);
        thread.start();
    }

    public void addEmployee(Employee employee) {
        this.employeesList.add(employee);
    }

    public int getCurrentCalls() {
        return currentCalls;
    }

    public TreeSet<Employee> getEmployeesList() {
        return employeesList;
    }

    public Queue<Call> getCallsWaitingList() {
        return callsWaitingList;
    }
}
