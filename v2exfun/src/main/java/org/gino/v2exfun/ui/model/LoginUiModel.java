package org.gino.v2exfun.ui.model;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.gino.v2exfun.MyApplication;
import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.data.serialize.model.Member;
import org.gino.v2exfun.data.serialize.model.V2exSession;
import org.gino.v2exfun.parser.V2exLoginParser;
import org.gino.v2exfun.data.serialize.http.RequestApis;
import org.gino.v2exfun.ui.model.event.LoginUiModelEvent;
import org.gino.v2exfun.utils.AsyncUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongzhuo on 14-1-16.
 */
public class LoginUiModel extends BaseUiModel<LoginUiModelEvent> {
//    private String mOnce;

    public void onLogin(String userName, String passWord) {
        final String tUserName = userName;
        final String tPassWord = passWord;

        AsyncUtils.excuteAsyncTask(new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                HashMap<String,String> cookieMaps = new HashMap<String, String>();
                final String mOnce = V2exLoginParser.getLoginOnceString(cookieMaps);

                Request request = RequestApis.getInstance().login(tUserName, tPassWord, mOnce,cookieMaps, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                if(V2exLoginParser.isLoginSucceed(s,tUserName)){
                                    List<HttpCookie> cookies = ((CookieManager) CookieHandler.getDefault()).getCookieStore().getCookies();
                                    CookieSyncManager.createInstance(MyApplication.getContext());
                                    for (HttpCookie cookie : cookies) {
                                        Log.e("TAG", "name=>" + cookie.getName() + "  value=>" + cookie.getValue());
                                        if(cookie.getName().equals("pb3_session")){
                                            for (LoginUiModelEvent event : LoginUiModel.this) {
                                                V2exSession v2exSession = new V2exSession(tUserName,cookie.getValue());
                                                Member member = new Member();
                                                member.setUsername(tUserName);
                                                event.onLoginSucceed(v2exSession, member);
                                            }
                                        }
                                        android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
                                        cookieManager.setCookie(ComConst.HTTP_BASE_POST_URL, cookie.getName() + "=" + cookie.getValue());
                                        CookieSyncManager.getInstance().sync();
                                    }
                                }else{
                                    for (LoginUiModelEvent event : LoginUiModel.this) {
                                        event.onLoginFaild();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }
                );
                executeRequest(request);

                return null;
            }
        });


    }
}
