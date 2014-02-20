package org.gino.v2exfun.parser;

import android.util.Log;

import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.utils.VLog;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuohong on 14-1-19.
 */
public class V2exLoginParser {
    public static String getLoginOnceString(HashMap<String,String> cookiesMaps) {
        String mOnce = null;
        try {
            Connection.Response res = Jsoup.connect(ComConst.HTTP_LOGIN_URL).execute();
            Document doc = res.parse();
            cookiesMaps.putAll(res.cookies());
            Elements links = doc.select("div.cell");
            Elements link = links.first().select("input[name=once]");
            mOnce = link.first().attr("value");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mOnce;
    }


    public static class LoginResponse{
        public boolean isLogined;
        public boolean errorMsg;
    }
    public static LoginResponse isLoginSucceed(String html,String userName){
        final String tUserName = userName;
        final LoginResponse result = new LoginResponse();
        Document document = Jsoup.parse(html);
        Elements usernameNodes = document.select("span.bigger");
        if (usernameNodes != null && usernameNodes.size() > 0){
            if(usernameNodes.select("a") != null && usernameNodes.select("a").first().text().equals(tUserName)){
                //成功
                result.isLogined = true;
            }else{
            }
        }else{
        }
        return result;
    }

    public static String doLogin(Map<String,String> cookieMaps,String userName, String passWord, String once){
        final String tUserName = userName;
        final String tPassWord = passWord;
        final String tOnce = once;
        try {
            Connection connection = Jsoup.connect(ComConst.HTTP_LOGIN_URL).cookies(cookieMaps).referrer(ComConst.HTTP_LOGIN_URL).method(Connection.Method.POST);
            connection.data("next","/");
            connection.data("u",tUserName);
            connection.data("once",tOnce);
            connection.data("p",tPassWord);
            connection.data("next","/");
            Connection.Response response = connection.execute();
            cookieMaps.putAll(response.cookies());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
