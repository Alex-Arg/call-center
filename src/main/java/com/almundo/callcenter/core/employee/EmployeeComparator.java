package com.almundo.callcenter.core.employee;

import com.almundo.callcenter.core.domain.employee.Employee;

import java.util.Comparator;

/**
 * Compares employees by the employee type
 */
public class EmployeeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getEmployeeType().compareTo(e2.getEmployeeType());
    }
}
