package com.almundo.callcenter.core.dispatchers.configuration;

import com.almundo.callcenter.core.call.CallFIFOQueue;
import com.almundo.callcenter.core.call.CallQueue;
import com.almundo.callcenter.core.dispatchers.Dispatcher;
import com.almundo.callcenter.core.employee.EmployeePriorityQueue;
import com.almundo.callcenter.core.employee.EmployeeQueue;
import com.almundo.callcenter.core.dispatchers.HierarchyPriorityDispatcher;
import com.almundo.callcenter.core.domain.employee.EmployeeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is the main configuration class for the spring app
 */
@Configuration
public class DispatcherConfiguration {

    private static final Integer CONCURRENT_CALL_SIZE = 10;
    private static final int OPERATORS_AMOUNT = 5;
    private static final int SUPERVISORS_AMOUNT = 3;
    private static final int DIRECTORS_AMOUNT = 2;

    @Bean
    public EmployeeQueue employeePriorityQueue() {
        EmployeePriorityQueue employeePriorityQueue = createEmployeesQueue();
        return employeePriorityQueue;
    }

    @Bean
    public CallQueue callPriorityQueue() {
        return new CallFIFOQueue();
    }

    @Bean
    public Dispatcher dispatcher(EmployeeQueue employeePriorityQueue, CallQueue callPriorityQueue) {
        return new HierarchyPriorityDispatcher(CONCURRENT_CALL_SIZE, employeePriorityQueue, callPriorityQueue);
    }

    private EmployeePriorityQueue createEmployeesQueue() {
        Integer totalEmployeeAmount = OPERATORS_AMOUNT + DIRECTORS_AMOUNT + SUPERVISORS_AMOUNT;
        EmployeePriorityQueue employeePriorityQueue = new EmployeePriorityQueue(totalEmployeeAmount);
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        for (int i = 0; i < OPERATORS_AMOUNT; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildOperator());
        }

        for (int i = 0; i < DIRECTORS_AMOUNT; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildDirector());
        }

        for (int i = 0; i < SUPERVISORS_AMOUNT; i++) {
            employeePriorityQueue.addEmployees(employeeBuilder.buildSupervisor());
        }
        return employeePriorityQueue;
    }
}
