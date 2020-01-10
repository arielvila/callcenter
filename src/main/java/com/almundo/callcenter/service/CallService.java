package com.almundo.callcenter.service;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Employee;

import static java.lang.Thread.sleep;

public class CallService implements Runnable {
    private Call call;
    private Dispatcher dispatcher;

    public CallService(Call call, Dispatcher dispatcher) {
        this.call = call;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            sleep(call.getDuration());
        } catch (InterruptedException e) {
            System.out.println("Fallo la llamada");
        } finally {
            Employee employee = this.call.hangUp();
            this.dispatcher.releaseEmployee(employee);
        }
    }
}
