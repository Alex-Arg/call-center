package com.almundo.callcenter.core.domain.employee;

import java.util.UUID;

public class EmployeeBuilder implements Builder {

    public Employee buildDirector() {
        return new Director(UUID.randomUUID().toString());
    }

    public Employee buildSupervisor() {
        return new Supervisor(UUID.randomUUID().toString());
    }

    public Employee buildOperator() {
        return new Operator(UUID.randomUUID().toString());
    }
}
