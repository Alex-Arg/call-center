package com.almundo.callcenter.core.dispatchers;

import com.almundo.callcenter.core.domain.call.Call;

public interface Dispatcher {

    void dispatchCall(Call call);

    Integer getDispatchedCallsAmount();
}
