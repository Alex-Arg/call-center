package com.almundo.callcenter.core.dispatchers;

import com.almundo.callcenter.core.call.CallQueue;
import com.almundo.callcenter.core.domain.call.Call;
import com.almundo.callcenter.core.employee.EmployeeQueue;
import com.almundo.callcenter.core.services.CallExecutorWorker;


/**
 * Handles incoming calls by queuing them in to be answered when workers are available.
 */
public class HierarchyPriorityDispatcher implements Dispatcher {

    private Integer workersSize;
    private EmployeeQueue employeeQueue;
    private CallQueue callQueue;
    private Integer dispatchedCallsAmount;

    public HierarchyPriorityDispatcher(Integer concurrentCallSize, EmployeeQueue employeeQueue, CallQueue callQueue) {
        this.workersSize = concurrentCallSize;
        this.employeeQueue = employeeQueue;
        this.callQueue = callQueue;
        dispatchedCallsAmount = 0;
        addWorkers();
    }

    /**
     * Add the call to a queue for being attended when employees are available.
     * @param call
     */
    @Override
    public void dispatchCall(Call call) {
        process(call);
        dispatchedCallsAmount++;
    }

    @Override
    public Integer getDispatchedCallsAmount() {
        return dispatchedCallsAmount;
    }

    private void process(Call call) {
        callQueue.put(call);
    }

    private void addWorkers() {
        for (int i = 0; i < workersSize; i++) {
            new CallExecutorWorker(callQueue, employeeQueue);
        }
    }
}