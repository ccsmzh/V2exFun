package org.gino.v2exfun.constant;

/**
 * Created by kefeng on 13-11-22.
 */
public class ComConst {
    private static final String HTTP_BASE_API_URL = "http://www.v2ex.com/api";
    private static final String HTTP_BASE_POST_URL = "http://www.v2ex.com";

    public static final String HTTP_IMAGE_BASE_URL = "http://www.v2ex.com";

    public static final String HTTP_TOPICS_LATEST_URL = HTTP_BASE_API_URL + "/topics/latest.json";
    public static final String HTTP_REPLIES_SHOW_URL = HTTP_BASE_API_URL + "/replies/show.json?topic_id=%1$d";
    public static final String HTTP_LOGIN_URL = HTTP_BASE_POST_URL + "/signin";

    public static final String HTTP_ALL_MAIN_LIST_JSOUP_URL = HTTP_BASE_POST_URL + "/?tab=all";
}
