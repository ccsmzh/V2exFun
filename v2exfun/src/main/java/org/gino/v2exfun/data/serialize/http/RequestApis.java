package org.gino.v2exfun.data.serialize.http;


import android.webkit.WebSettings;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import org.apache.http.protocol.HTTP;
import org.gino.v2exfun.MyApplication;
import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.data.serialize.model.Reply;
import org.gino.v2exfun.data.serialize.model.Topic;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zh.GiNo. on 13-11-23.
 */
public class RequestApis {
    private static RequestApis mInstance = null;

    public static RequestApis getInstance() {
        if (mInstance == null) {
            mInstance = new RequestApis();
        }

        return mInstance;
    }

    public Request login(String userName, String pwd, String once, Map<String,String> cookies, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        final String tUserName = userName;
        final String tPwd = pwd;
        final String tOnce = once;
        final Map<String,String> tCookies = cookies;
        Request request = new StringRequest(Request.Method.POST, ComConst.HTTP_LOGIN_URL, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new IdentityHashMap<String, String>();
                params.put("next", "/");
                params.put("u", tUserName);
                params.put("once", tOnce);
                params.put("p", tPwd);
                params.put("next", "/");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if(headers == null || headers.size() == 0){
                    headers = new HashMap<String, String>();
                }
                StringBuilder builder = new StringBuilder();
                Iterator iterator = tCookies.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    builder.append(key)
                            .append("=")
                            .append(val);
                    if (iterator.hasNext())
                        builder.append("; ");
                }
                headers.put(HTTP.USER_AGENT,WebSettings.getDefaultUserAgent(MyApplication.getContext()));
                headers.put("Accept", "*/*");
                headers.put("Connection", "keep-alive");
                headers.put("Cookie",builder.toString());
                headers.put("Referer",ComConst.HTTP_LOGIN_URL);
                return headers;
            }
        };
        return request;
    }

    /**
     * 获取最新20主题
     *
     * @param listener
     * @param errorListener
     * @return
     */
    public Request getLatestTopics(Response.Listener<List<Topic>> listener, Response.ErrorListener errorListener) {
        Type type = new TypeToken<List<Topic>>() {
        }.getType();
        Request request = new GsonArrayRequest<List<Topic>>(ComConst.HTTP_TOPICS_LATEST_URL, type, null, listener, errorListener);
        return request;
    }

    /**
     * 获取主题回复列表
     *
     * @param topicId
     * @param listener
     * @param errorListener
     * @return
     */
    public Request getRepliesForTopicId(int topicId, Response.Listener<List<Reply>> listener, Response.ErrorListener errorListener) {
        Type type = new TypeToken<List<Reply>>() {
        }.getType();
        Request request = new GsonArrayRequest<List<Reply>>(String.format(ComConst.HTTP_REPLIES_SHOW_URL, topicId), type, null, listener, errorListener);
        return request;
    }

}
