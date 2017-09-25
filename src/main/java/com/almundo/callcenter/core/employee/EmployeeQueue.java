package com.almundo.callcenter.core.employee;

import com.almundo.callcenter.core.domain.employee.Employee;

/**
 * Implement this to add different behavior to employees queues
 */
public interface EmployeeQueue {
    Employee takeEmployee();

    void addEmployees(Employee employee);

    boolean queueIsEmpty();

    void returnEmployeeToQueue(Employee assignedEmployee);
}
