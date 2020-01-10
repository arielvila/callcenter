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

        assertTrue(dispatcher.getEmployeesList().size() == 1);
    }

    @Test
    public void shouldTakeCallFromQueueAfterReleasingEmployee() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.dispatchCall(new Call());

        assertTrue(dispatcher.getCallsWaitingList().size() == 1);

        dispatcher.releaseEmployee(new Operator("Juan"));

        assertTrue(dispatcher.getCurrentCalls() == 1);
    }


    @Test
    public void currentCallsCantBeNegativeAfterReleasingEmployee() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.releaseEmployee(new Operator("Juan"));

        assertTrue(dispatcher.getCurrentCalls() == 0);
    }
}
