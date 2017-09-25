package com.almundo.callcenter.core.call;

import com.almundo.callcenter.core.dispatchers.Dispatcher;
import com.almundo.callcenter.core.domain.call.Call;
import com.almundo.callcenter.core.domain.call.PhoneCall;

import java.util.Random;

/**
 * Create calls to be passed to de dispatcher.
 */
public class CallMaker implements Runnable {

    private Dispatcher dispatcher;
    private int callQty;
    private Long callCounter;
    private Random random;

    private static final long CALL_LOWER_DURATION_RANGE = 5;
    private static final long CALL_UPPER_DURATION_RANGE = 10;

    public CallMaker(Dispatcher dispatcher, int callQty) {
        this.dispatcher = dispatcher;
        this.callQty = callQty;
        this.callCounter = 0L;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (callQty > callCounter) {
            Call call = new PhoneCall(callCounter++, getRandomDurationInSeconds());
            dispatcher.dispatchCall(call);
            // Could add an sleep thread to get console output in the right order.
        }
    }

    private long getRandomDurationInSeconds() {
        return ((long) (CALL_LOWER_DURATION_RANGE + (random.nextDouble() * (CALL_UPPER_DURATION_RANGE - CALL_LOWER_DURATION_RANGE)))) * 1000;
    }

    public Long getCallCounter() {
        return callCounter;
    }
}
