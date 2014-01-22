package org.gino.v2exfun.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hongzhuo on 14-1-22.
 */
public class ResizeTextView extends TextView {

    private OnResizeListener mListener;

    public ResizeTextView(Context context) {
        this(context, null);
    }

    public ResizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ResizeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if(mListener != null){
            mListener.onResize(changed,left,top,right,bottom);
        }
    }

    public void setOnResizeListener(OnResizeListener mListener) {
        this.mListener = mListener;
    }

    public interface  OnResizeListener{
        void onResize(boolean changed, int left, int top, int right, int bottom);
    }
}
