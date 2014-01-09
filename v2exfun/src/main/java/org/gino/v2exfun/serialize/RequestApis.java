package org.gino.v2exfun.serialize;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.serialize.model.Reply;
import org.gino.v2exfun.serialize.model.Topic;

import java.lang.reflect.Type;
import java.util.HashMap;
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

    public Request login(String userName,String pwd,Response.Listener<String> listener, Response.ErrorListener errorListener){
        final String tUserName = userName;
        final String tPwd = pwd;
        Request request = new StringRequest(Request.Method.POST, ComConst.HTTP_LOGIN_URL,listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("u", tUserName);
                params.put("p", tPwd);
                return params;
            }
        };
        return request;
    }

    /**
     * 获取最新20主题
     * @param listener
     * @param errorListener
     * @return
     */
    public Request getLatestTopics(Response.Listener<List<Topic>> listener, Response.ErrorListener errorListener) {
        Type type = new TypeToken<List<Topic>>(){}.getType();
        Request request = new GsonArrayRequest<List<Topic>>(ComConst.HTTP_TOPICS_LATEST_URL,type, null, listener, errorListener);
        return request;
    }

    /**
     * 获取主题回复列表
     * @param topicId
     * @param listener
     * @param errorListener
     * @return
     */
    public Request getRepliesForTopicId(int topicId,Response.Listener<List<Reply>> listener, Response.ErrorListener errorListener){
        Type type = new TypeToken<List<Reply>>(){}.getType();
        Request request = new GsonArrayRequest<List<Reply>>(String.format(ComConst.HTTP_REPLIES_SHOW_URL,topicId),type,null,listener,errorListener);
        return request;
    }

}
