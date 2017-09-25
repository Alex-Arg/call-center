package com.almundo.callcenter.core.domain.employee;

public class Operator extends Employee implements Comparable<Employee> {

    public Operator(String id) {
        super(id, EmployeeType.OPERATOR);
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.getEmployeeType().getPriorityOrder(), other.getEmployeeType().getPriorityOrder());
    }
}
