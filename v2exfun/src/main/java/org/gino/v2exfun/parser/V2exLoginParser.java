package org.gino.v2exfun.parser;

import org.gino.v2exfun.constant.ComConst;
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

    public static boolean isLoginSucceed(String html,String userName){
        final String tUserName = userName;

        Document document = Jsoup.parse(html);
        Elements usernameNodes = document.select("span.bigger");
        if (usernameNodes != null && usernameNodes.size() > 0){
            if(usernameNodes.select("a") != null && usernameNodes.select("a").first().text().equals(tUserName)){
                //成功
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
