package com.almundo.callcenter.core.domain.employee;

public class Director extends Employee implements Comparable<Employee> {

    public Director(String id) {
        super(id, EmployeeType.DIRECTOR);
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.getEmployeeType().getPriorityOrder(), other.getEmployeeType().getPriorityOrder());
    }
}

