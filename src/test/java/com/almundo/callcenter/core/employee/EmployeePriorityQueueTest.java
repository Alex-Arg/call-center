package com.almundo.callcenter.core.employee;

import com.almundo.callcenter.core.domain.employee.Employee;
import com.almundo.callcenter.core.domain.employee.EmployeeBuilder;
import com.almundo.callcenter.core.domain.employee.EmployeeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class EmployeePriorityQueueTest {

    private EmployeeQueue employeeQueue;
    private LinkedList<Employee> takenEmployeesList = new LinkedList();
    private static final int OPERATORS_AMOUNT = 5;
    private static final int SUPERVISORS_AMOUNT = 3;
    private static final int DIRECTORS_AMOUNT = 2;

    @Test
    public void givenAQueueOfEmployeesAreAssignedInPriorityOrder() {
        givenAQueueOfEmployees();
        whenAllOfThemAreBeingTaken();
        thenTheEmployeesWereAssignedInPriorityOrder();
    }

    private void givenAQueueOfEmployees() {
        employeeQueue = new EmployeePriorityQueue(OPERATORS_AMOUNT + SUPERVISORS_AMOUNT + DIRECTORS_AMOUNT);

        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        for (int i = 0; i < OPERATORS_AMOUNT; i++) {
            employeeQueue.addEmployees(employeeBuilder.buildOperator());
        }

        for (int i = 0; i < SUPERVISORS_AMOUNT; i++) {
            employeeQueue.addEmployees(employeeBuilder.buildDirector());
        }

        for (int i = 0; i < DIRECTORS_AMOUNT; i++) {
            employeeQueue.addEmployees(employeeBuilder.buildSupervisor());
        }
    }

    private void thenTheEmployeesWereAssignedInPriorityOrder() {
        for (int i = 0; i < OPERATORS_AMOUNT; i++) {
            Assert.assertTrue("First Taken Are Operators", takenEmployeesList.get(i).getEmployeeType().equals(EmployeeType.OPERATOR));
        }

        for (int i = OPERATORS_AMOUNT; i < OPERATORS_AMOUNT + SUPERVISORS_AMOUNT - 1; i++) {
            Assert.assertTrue("Second Taken Are Supervisors", takenEmployeesList.get(i).getEmployeeType().equals(EmployeeType.SUPERVISOR));
        }

        for (int i = OPERATORS_AMOUNT + SUPERVISORS_AMOUNT; i < OPERATORS_AMOUNT + SUPERVISORS_AMOUNT + DIRECTORS_AMOUNT - 1; i++) {
            Assert.assertTrue("Third Taken Are Directors", takenEmployeesList.get(i).getEmployeeType().equals(EmployeeType.DIRECTOR));
        }
    }

    private void whenAllOfThemAreBeingTaken() {
        while (!employeeQueue.queueIsEmpty()) {
            Employee employee = employeeQueue.takeEmployee();
            this.takenEmployeesList.add(employee);
        }
    }
}
