package com.almundo.callcenter;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Director;
import com.almundo.callcenter.model.Operator;
import com.almundo.callcenter.model.Supervisor;
import com.almundo.callcenter.service.Dispatcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class DispatcherTest
{
    @Test
    public void shouldProcess10CallsWith10Employees() {
        Dispatcher dispatcher = new Dispatcher();
        for (int i=0; i<8; i++) {
            dispatcher.addEmployee(new Operator("Operator " + i));
        }
        dispatcher.addEmployee(new Director("Director"));
        dispatcher.addEmployee(new Supervisor("Supervisor"));

        for (int i=0; i<10; i++) {
            dispatcher.dispatchCall(new Call());
        }

        assertEquals(dispatcher.getCurrentCalls(), 10);
    }

    @Test
    public void shouldNotProcess11Calls() {
        Dispatcher dispatcher = new Dispatcher();
        for (int i=0; i<11; i++) {
            dispatcher.addEmployee(new Operator("Operator " + i));
        }

        for (int i=0; i<11; i++) {
            dispatcher.dispatchCall(new Call());
        }

        assertEquals(dispatcher.getCurrentCalls(), 10);
        assertEquals(dispatcher.getCallsWaitingList().size(), 1);
    }

    @Test
    public void shouldNotProcessWithoutEmployees() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.dispatchCall(new Call());

        assertNotEquals(dispatcher.getCurrentCalls(), 1);

    }

    @Test
    public void shouldHave1EmployeeAfterReleasingHim() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.releaseEmployee(new Operator("Juan"));

        assertEquals(dispatcher.getEmployeesList().size(), 1);
    }

    @Test
    public void shouldTakeCallFromQueueAfterReleasingEmployee() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.dispatchCall(new Call());

        assertEquals(dispatcher.getCallsWaitingList().size(), 1);

        dispatcher.releaseEmployee(new Operator("Juan"));

        assertEquals(dispatcher.getCurrentCalls(), 1);
    }


    @Test
    public void currentCallsCantBeNegativeAfterReleasingEmployee() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.releaseEmployee(new Operator("Juan"));

        assertEquals(dispatcher.getCurrentCalls(), 0);
    }

    @Test
    public void shouldProcess10CallsWithThreadsWith10Employees() {
        Dispatcher dispatcher = new Dispatcher();
        for (int i=0; i<8; i++) {
            dispatcher.addEmployee(new Operator("Operator " + i));
        }
        dispatcher.addEmployee(new Director("Director"));
        dispatcher.addEmployee(new Supervisor("Supervisor"));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dispatcher.dispatchCall(new Call());
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        Thread thread5 = new Thread(runnable);
        Thread thread6 = new Thread(runnable);
        Thread thread7 = new Thread(runnable);
        Thread thread8 = new Thread(runnable);
        Thread thread9 = new Thread(runnable);
        Thread thread10 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(dispatcher.getCurrentCalls(), 10);
    }
}
