package com.almundo.callcenter.core.call;

import com.almundo.callcenter.core.call.exceptions.CallQueueException;
import com.almundo.callcenter.core.domain.call.Call;
import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Class in charge of managing the queue of incoming calls to be attended by available employees.
 * This queue uses an ArrayBlockingQueue implementation, which allows it to get items in a FIFO priority.
 */
public class CallFIFOQueue implements CallQueue {

    public static final int CAPACITY = 500;
    private static Logger logger = Logger.getLogger(CallFIFOQueue.class);

    BlockingQueue<Call> callQueue;

    public CallFIFOQueue() {
        this.callQueue = new ArrayBlockingQueue(CAPACITY);
    }

    @Override
    public void put(Call call) {
        try {
            callQueue.put(call);
        } catch (InterruptedException e) {
            logger.error(getErrorDispatcherMessage(call));
            throw new CallQueueException(getErrorDispatcherMessage(call));
        }
    }

    @Override
    public Call get() {
        return callQueue.poll();
    }

    private String getErrorDispatcherMessage(Call call) {
        return String.format("call number %s could not be processed", call.getId());
    }
}
