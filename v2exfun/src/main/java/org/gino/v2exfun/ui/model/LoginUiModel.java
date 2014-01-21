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
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
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


                return null;
            }
        });


    }
}
