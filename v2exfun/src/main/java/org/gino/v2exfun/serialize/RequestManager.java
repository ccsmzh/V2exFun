package org.gino.v2exfun.serialize;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.gino.v2exfun.MyApplication;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by zh.GiNo. on 13-11-23.
 * Refer to https://github.com/Issacw0ng/Dribbo/blob/master/Driibo/src/main/java/com/refactech/driibo/data/RequestManager.java
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class RequestManager {
    public static RequestQueue mRequestQueue = newRequestQueue();
    public static final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
    public static ImageLoader.ImageCache mImageCache = new ImageLoader.ImageCache() {
        @Override
        public Bitmap getBitmap(String s) {
            return lruCache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            lruCache.put(s,bitmap);
        }
    };
    public static ImageLoader mImageLoader = new ImageLoader(mRequestQueue,mImageCache);

    public static RequestQueue newRequestQueue(){
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(cookieManager);

        RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());

        queue.start();
        return queue;
    }

    public static void addRequest(Request request){
        mRequestQueue.add(request);
    }

    public static ImageLoader.ImageContainer loadImage(String url, ImageLoader.ImageListener listener){
        return mImageLoader.get(url,listener);
    }
}
