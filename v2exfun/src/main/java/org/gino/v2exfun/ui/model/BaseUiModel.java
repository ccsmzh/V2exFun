package org.gino.v2exfun.ui.model;

import com.android.volley.Request;

import org.gino.v2exfun.data.serialize.http.RequestManager;
import org.gino.v2exfun.ui.model.event.BaseUiModelEvent;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hongzhuo on 14-1-13.
 */
public abstract class BaseUiModel{
    protected void executeRequest(Request request) {
        RequestManager.addRequest(request);
    }
}
