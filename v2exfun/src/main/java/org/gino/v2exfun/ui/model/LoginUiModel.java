package org.gino.v2exfun.ui.model;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.gino.v2exfun.MyApplication;
import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.serialize.RequestApis;
import org.gino.v2exfun.ui.model.event.LoginUiModelEvent;
import org.gino.v2exfun.utils.AsyncUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

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
                try {
                    Connection.Response res = Jsoup.connect(ComConst.HTTP_LOGIN_URL).execute();
                    Document doc = res.parse();
                    Map<String,String> cookieMaps = res.cookies();
                    Elements links = doc.select("div.cell");
                    Elements link = links.first().select("input[name=once]");
                    final String mOnce = link.first().attr("value");
                    Log.e("TAG","once==>"+mOnce);

//                    List<HttpCookie> cookies = ((CookieManager) CookieHandler.getDefault()).getCookieStore().getCookies();
//                    CookieSyncManager.createInstance(MyApplication.getContext());
//                    for (HttpCookie cookie : cookies) {
//                        Log.e("TAG", "name=>" + cookie.getName() + "  value=>" + cookie.getValue());
//                        android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
//                        cookieManager.setCookie(ComConst.HTTP_BASE_POST_URL, cookie.getName() + "=" + cookie.getValue());
//                        CookieSyncManager.getInstance().sync();
//                    }

                    Request request = RequestApis.getInstance().login(tUserName, tPassWord, mOnce,cookieMaps, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    Log.e("TAG",s);
                                    List<HttpCookie> cookies = ((CookieManager) CookieHandler.getDefault()).getCookieStore().getCookies();
                                    CookieSyncManager.createInstance(MyApplication.getContext());
                                    for (HttpCookie cookie : cookies) {
                                        Log.e("TAG", "name=>" + cookie.getName() + "  value=>" + cookie.getValue());
                                        android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
                                        cookieManager.setCookie(ComConst.HTTP_BASE_POST_URL, cookie.getName() + "=" + cookie.getValue());
                                        CookieSyncManager.getInstance().sync();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }
                    );
                    executeRequest(request);
                    for (LoginUiModelEvent event : LoginUiModel.this) {
                        event.onLogin(tUserName, tPassWord);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


    }
}