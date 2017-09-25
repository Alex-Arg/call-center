package com.almundo.callcenter.core.services;

import com.almundo.callcenter.core.call.CallMaker;
import com.almundo.callcenter.core.dispatchers.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityCallService implements CallService {

    @Autowired
    private Dispatcher dispatcher;

    /**
     * Create the main producer Thread that consumes the calls
     *
     * @param callQty
     */
    @Override
    public void process(int callQty) {
        new Thread(new CallMaker(dispatcher, callQty)).start();
    }

}
