package org.gino.v2exfun.ui.fragment;

import android.support.v4.app.Fragment;

import com.android.volley.Request;

import org.gino.v2exfun.serialize.RequestManager;

/**
 * Created by zh.GiNo. on 13-11-23.
 */
public class BaseFragment extends Fragment{
    protected void executeRequest(Request request) {
        RequestManager.addRequest(request);
    }
}
