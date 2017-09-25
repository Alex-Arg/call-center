package com.almundo.callcenter.core.domain.employee;

public enum EmployeeType {
    OPERATOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);

    private Integer priorityOrder;

    EmployeeType(Integer priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    public Integer getPriorityOrder() {
        return priorityOrder;
    }
}
