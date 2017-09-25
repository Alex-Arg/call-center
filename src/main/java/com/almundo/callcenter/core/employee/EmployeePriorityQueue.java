package com.almundo.callcenter.core.employee;

import com.almundo.callcenter.core.domain.employee.Employee;
import com.almundo.callcenter.core.employee.exceptions.EmployeeQueueException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * class in charge of managing the queue of available employees according to the priority in the hierarchical scale.
 * For this task it uses the PriorityBlockingQueue implementation of BlockingQueue, which allows to obtain elements according
 * to the priority condition indicated by them.
 * In this EmployeeQueue implementation the priority is indicated by hierarchical scale of the employee.
 * 1 - Operator. 2-Supevisor. 3.Director.
 */
public class EmployeePriorityQueue implements EmployeeQueue {

    BlockingQueue<Employee> employeeQueue;

    public EmployeePriorityQueue(Integer totalEmployeeAmount) {
        this.employeeQueue = new PriorityBlockingQueue(totalEmployeeAmount, new EmployeeComparator());
    }

    /**
     * Takes an employee from the queue
     *
     * @return
     */
    @Override
    public Employee takeEmployee() {
        try {
            return employeeQueue.take();
        } catch (InterruptedException e) {
            throw new EmployeeQueueException("Cant take employee from the employees queue");
        }
    }

    /**
     * Add Employees to the queue
     *
     * @param employee
     */
    @Override
    public void addEmployees(Employee employee) {
        employeeQueue.add(employee);
    }

    @Override
    public boolean queueIsEmpty() {
        return employeeQueue.isEmpty();
    }

    /**
     * Use this method to return an idle employee to the queue
     *
     * @param assignedEmployee
     */
    @Override
    public void returnEmployeeToQueue(Employee assignedEmployee) {
        try {
            employeeQueue.put(assignedEmployee);
        } catch (InterruptedException e) {
            throw new EmployeeQueueException(String.format("Cant return employee %s to the employees queue", assignedEmployee.getId()));
        }
    }
}
