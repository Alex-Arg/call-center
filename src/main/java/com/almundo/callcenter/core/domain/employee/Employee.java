package com.almundo.callcenter.core.domain.employee;

public abstract class Employee implements Comparable<Employee> {

    private String id;
    private EmployeeType employeeType;

    public Employee(String id, EmployeeType employeeType) {
        this.id = id;
        this.employeeType = employeeType;
    }

    public String getId() {
        return id;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }
}
