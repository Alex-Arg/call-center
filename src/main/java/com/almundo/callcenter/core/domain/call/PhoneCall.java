package com.almundo.callcenter.core.domain.call;

import com.almundo.callcenter.core.call.exceptions.CallException;
import com.almundo.callcenter.core.domain.call.Call;
import com.almundo.callcenter.core.domain.employee.Employee;

import java.time.LocalDateTime;

public class PhoneCall implements Call {

    private Long callId;
    private Long duration;
    private Employee employee;
    private LocalDateTime finishTime;

    public PhoneCall(Long callId, Long duration) {
        this.callId = callId;
        this.duration = duration;
    }

    @Override
    public void endCall() {
        finishTime = LocalDateTime.now();
    }

    @Override
    public void assignEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Employee getAssignedEmployee() {
        return employee;
    }

    /**
     * Method used for answer the call. Simulating the call duration.
     */
    @Override
    public void attendCall() {
        try {
            long start = System.currentTimeMillis();
            System.out.println("->ATTEND CALL " + callId + " attended by employee " + employee.getId() + " Type: " + employee.getEmployeeType().toString());
            Thread.sleep(getDuration());
            endCall();
            long durationInSeconds = (System.currentTimeMillis() - start) / 1000;
            System.out.println("->END CALL " + callId + " attended by employee " + employee.getId() + " Type: " + employee.getEmployeeType().toString() + " Call duration " + durationInSeconds + "seg.");
        } catch (InterruptedException e) {
            throw new CallException(String.format("Problem when trying to attend the call %s , attended by employee %s", callId, employee.getId()));
        }
    }

    @Override
    public Long getId() {
        return callId;
    }

    public Long getDuration() {
        return duration;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

}
