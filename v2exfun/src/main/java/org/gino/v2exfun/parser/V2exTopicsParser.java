package org.gino.v2exfun.parser;

import android.util.Log;

import org.gino.v2exfun.constant.ComConst;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhuohong on 14-1-6.
 */
public class V2exTopicsParser {
    public void getV2exNearly50Tipics(){
        try {
            Document doc = Jsoup.connect(ComConst.HTTP_ALL_MAIN_LIST_JSOUP_URL).get();
//                                                                                     Elements links = doc.select("div[class=\"cell item\"]");
            Elements links = doc.select("div.cell.item");
            for(Element element : links){
                Log.e("test", element.select("span.item_title").first().text());
            }
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
