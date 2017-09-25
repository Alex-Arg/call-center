package com.almundo.callcenter.core.services;

import com.almundo.callcenter.core.domain.call.Call;
import com.almundo.callcenter.core.call.CallQueue;
import com.almundo.callcenter.core.employee.EmployeeQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create the consumer workers
 */
public class CallExecutorWorker implements Runnable {

    private ExecutorService executor;
    private CallQueue callQueue;
    private EmployeeQueue employeeQueue;

    public CallExecutorWorker(CallQueue callQueue, EmployeeQueue employeeQueue) {
        this.callQueue = callQueue;
        this.employeeQueue = employeeQueue;
        executor = Executors.newSingleThreadExecutor();
        submitToQueue();
    }

    @Override
    public void run() {
        while (true) {
            Call call = callQueue.get();
            try {
                if (call != null) {
                    call.assignEmployee(employeeQueue.takeEmployee());
                    call.attendCall();
                    releaseEmployee(call);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void releaseEmployee(Call call) {
        employeeQueue.returnEmployeeToQueue(call.getAssignedEmployee());
    }

    public void submitToQueue() {
        executor.execute(this);
    }
}