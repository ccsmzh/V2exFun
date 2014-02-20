package org.gino.v2exfun.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

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

    public static int getDisplayWidth(Context context){
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getDisplayHeight(Context context){
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
