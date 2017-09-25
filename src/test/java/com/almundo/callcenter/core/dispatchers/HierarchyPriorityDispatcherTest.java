package com.almundo.callcenter.core.dispatchers;

import com.almundo.callcenter.core.call.CallFIFOQueue;
import com.almundo.callcenter.core.call.CallQueue;
import com.almundo.callcenter.core.domain.call.PhoneCall;
import com.almundo.callcenter.core.domain.call.Call;
import com.almundo.callcenter.core.domain.employee.EmployeeBuilder;
import com.almundo.callcenter.core.employee.EmployeePriorityQueue;
import com.almundo.callcenter.core.employee.EmployeeQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class HierarchyPriorityDispatcherTest {

    private static final Integer CALL_AMOUNT = 10;
    private Dispatcher dispatcher;
    private EmployeeQueue employeePriorityQueue;
    private static final long CALL_LOWER_DURATION_RANGE = 5;
    private static final long CALL_UPPER_DURATION_RANGE = 10;
    private Random random;
    private List<Call> phoneCallList;

    @Before
    public void init() {
        CallQueue callQueue = new CallFIFOQueue();
        Integer employeeSize = 10;
        // (0) - Dont want test workers in this test. Only dispatcher
        this.dispatcher = new HierarchyPriorityDispatcher(0, employeePriorityQueue, callQueue);
        this.random = new Random();
        this.phoneCallList = new ArrayList();
    }

    @Test
    public void whenCallsAreSubmittedThenTheCallsAreProcessed() {
        givenAGroupOfEmployees();
        givenAGroupOfPhoneCalls(CALL_AMOUNT);
        whenCallsAreSubmitted();
        waitForCallsToBeProcessed(50 * 10);
        thenTheCallsWereDispatched();
    }

    private void waitForCallsToBeProcessed(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            Assert.fail();
        }
    }

    private void givenAGroupOfEmployees() {
        int operatorsAmount = 5;
        int supervisorsAmount = 3;
        int directorsAmount = 2;
        Integer totalEmployeeAmount = operatorsAmount + supervisorsAmount + directorsAmount;

        employeePriorityQueue = new EmployeePriorityQueue(totalEmployeeAmount);
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        for (int i = 0; i < operatorsAmount; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildOperator());
        }

        for (int i = 0; i < supervisorsAmount; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildDirector());
        }

        for (int i = 0; i < directorsAmount; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildSupervisor());
        }
    }

    private void givenAGroupOfPhoneCalls(int callaAmount) {

        for (int i = 0; i < callaAmount; i++) {
            Call call = new PhoneCall(Long.valueOf(i), getRandomDurationInSeconds());
            phoneCallList.add(call);
        }
    }

    private void whenCallsAreSubmitted() {
        phoneCallList.forEach(call -> dispatcher.dispatchCall(call));
    }

    private void thenTheCallsWereDispatched() {
        assertTrue("Same amount of dispatched calls", CALL_AMOUNT.equals(dispatcher.getDispatchedCallsAmount()));
    }

    private long getRandomDurationInSeconds() {
        return ((long) (CALL_LOWER_DURATION_RANGE + (random.nextDouble() * (CALL_UPPER_DURATION_RANGE - CALL_LOWER_DURATION_RANGE)))) * 1000;
    }
}
