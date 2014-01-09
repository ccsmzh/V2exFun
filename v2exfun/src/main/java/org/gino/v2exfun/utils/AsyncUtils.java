package org.gino.v2exfun.utils;

import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by zh.GiNo. on 13-11-23.
 */
public class AsyncUtils {
    /**
     *
     * @param task
     * @param params
     * @param <Params>
     * @param <Progress>
     * @param <Result>
     *     Refer to https://github.com/Issacw0ng/Dribbo/blob/master/Driibo/src/main/java/com/refactech/driibo/util/CommonUtils.java
     */
    public static <Params, Progress, Result> void excuteAsyncTask(AsyncTask<Params, Progress, Result> task, Params... params){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }
}
