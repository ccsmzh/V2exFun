package org.gino.v2exfun.parser;

import com.google.gson.internal.LazilyParsedNumber;

import org.gino.v2exfun.constant.ComConst;
import org.gino.v2exfun.data.serialize.model.Member;
import org.gino.v2exfun.data.serialize.model.Node;
import org.gino.v2exfun.data.serialize.model.Topic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuohong on 14-1-6.
 */
public class V2exTopicsParser {
    public List<Topic> getV2exNearly50Tipics() {
        List<Topic> tmpTopics = new ArrayList<Topic>();
        try {
            Document doc = Jsoup.connect(ComConst.HTTP_ALL_MAIN_LIST_JSOUP_URL).get();
            Elements links = doc.select("div.cell.item");
            for (Element element : links) {
                Topic tmpTopic = new Topic();
                tmpTopic.setTitle(element.select("span.item_title").first().text());
                tmpTopic.setId(new LazilyParsedNumber(element.select("span.item_title").select("a").attr("href")));
                Elements tmpCountLividLink = element.select("a.count_livid");
                if(tmpCountLividLink != null && tmpCountLividLink.size() > 0){
                    tmpTopic.setReplies(new LazilyParsedNumber(element.select("a.count_livid").first().text()));
                }else{
                    tmpTopic.setReplies(new LazilyParsedNumber("0"));
                }
                String tmpTimeStr = element.select("span.small.fade").text().split("•")[1];
                tmpTopic.setCreatedTimeShowStr(tmpTimeStr.substring(tmpTimeStr.indexOf(" ", 2)).replace(" ", ""));


                Node tmpNode = new Node();
                Elements smallLinks = element.select("span.small.fade").select("a[href]");
                tmpNode.setTitle(smallLinks.first().text());
                tmpTopic.setNode(tmpNode);

                Member tmpMember = new Member();
                tmpMember.setUsername(smallLinks.get(1).text());
                tmpMember.setAvatar_normal(element.select("img.avatar").attr("src"));
                tmpMember.setAvatar_large(element.select("img.avatar").attr("src"));
                tmpMember.setAvatar_mini(element.select("img.avatar").attr("src"));
                tmpTopic.setMember(tmpMember);

                tmpTopics.add(tmpTopic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpTopics;
    }
}
