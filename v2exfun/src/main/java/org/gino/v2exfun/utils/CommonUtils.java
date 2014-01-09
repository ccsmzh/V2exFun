package org.gino.v2exfun.utils;

/**
 * Created by zhuohong on 13-12-4.
 */
public class CommonUtils {

    public static String getLastTime(long lastTime){
        long nowTime = System.currentTimeMillis();
        long diffSecond = nowTime / 1000 - lastTime;

        if (diffSecond < 60) {
            return "刚刚";
        } else if (diffSecond < (60 * 60)) {
            return diffSecond / 60 + "分钟前";
        } else if (diffSecond < 60 * 60 * 60) {
            return diffSecond / 60 / 60 + "小时前";
        }
        return null;
    }
}
