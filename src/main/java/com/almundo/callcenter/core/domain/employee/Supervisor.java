package com.almundo.callcenter.core.domain.employee;

public class Supervisor extends Employee implements Comparable<Employee> {

    public Supervisor(String id) {
        super(id, EmployeeType.SUPERVISOR);
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.getEmployeeType().getPriorityOrder(), other.getEmployeeType().getPriorityOrder());
    }
}
