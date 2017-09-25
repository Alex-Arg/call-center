package com.almundo.callcenter.core.domain.call;

import com.almundo.callcenter.core.domain.employee.Employee;

public interface Call {
    Long getId();

    void endCall();

    void assignEmployee(Employee employee);

    Employee getAssignedEmployee();

    void attendCall();
}
