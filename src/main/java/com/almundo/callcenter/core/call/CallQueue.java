package com.almundo.callcenter.core.call;

import com.almundo.callcenter.core.domain.call.Call;

/**
 * Implement this interface to add differents  calls queue behaviors.
 */
public interface CallQueue {
    void put(Call call);

    Call get();
}
