package com.almundo.callcenter.service;

import com.almundo.callcenter.model.*;

import java.util.*;

public class Dispatcher {
    public final static int MAX_CONCURRENT_DISPATCH = 10;

    private TreeSet<Employee> employeesList = new TreeSet();
    private ArrayDeque<Call> callsWaitingList = new ArrayDeque();
    private int currentCalls = 0;

    /**
     * Se encarga de asignar empleado al llamado y, si no es posible, encola la llamada
     *
     * @param call
     */
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

    /**
     * Libera empleado y linea. Si hay llamadas esperando, despacha la mas vieja
     *
     * @param employee
     */
    public synchronized void releaseEmployee(Employee employee) {
        currentCalls = currentCalls > 0 ? currentCalls - 1 : 0;
        this.employeesList.add(employee);
        if (callsWaitingList.size() > 0) {
            Call call = callsWaitingList.remove();
            this.dispatchCall(call);
        }
    }

    /**
     * Dispara llamada
     *
     * @param call
     */
    private void dispatch(Call call) {
        Runnable callService = new CallService(call,this);
        Thread thread = new Thread(callService);
        thread.start();
    }

    /**
     * Disponibiliza un nuevo empleado
     *
     * @param employee
     */
    public void addEmployee(Employee employee) {
        this.employeesList.add(employee);
    }

    /**
     * Muestra cantidad de llamadas activas.
     *
     * @return currentCalls
     */
    public int getCurrentCalls() {
        return currentCalls;
    }

    /**
     * Muestra los empleados disponibles sin asignar.
     *
     * @return employees
     */
    public TreeSet<Employee> getEmployeesList() {
        return employeesList;
    }


    /**
     * Muestra llamados en espera
     *
     * @return callsWaiting
     */
    public Queue<Call> getCallsWaitingList() {
        return callsWaitingList;
    }
}
