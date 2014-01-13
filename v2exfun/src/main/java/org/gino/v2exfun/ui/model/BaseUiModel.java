package org.gino.v2exfun.ui.model;

import com.android.volley.Request;

import org.gino.v2exfun.serialize.RequestManager;
import org.gino.v2exfun.ui.model.event.BaseUiModelEvent;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hongzhuo on 14-1-13.
 */
public abstract class BaseUiModel<T extends BaseUiModelEvent> implements Iterable<T>{
    private final ConcurrentLinkedQueue<T> mEvents;

    public BaseUiModel(){
        mEvents = new ConcurrentLinkedQueue<T>();
    }

    @Override
    public Iterator<T> iterator() {
        return mEvents.iterator();
    }

    public void regEvent(T event){
        mEvents.add(event);
    }

    protected void executeRequest(Request request) {
        RequestManager.addRequest(request);
    }
}
