package org.gino.v2exfun;

import android.app.Application;
import android.content.Context;

/**
 * Created by zh.GiNo. on 13-11-23.
 */
public class MyApplication extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
