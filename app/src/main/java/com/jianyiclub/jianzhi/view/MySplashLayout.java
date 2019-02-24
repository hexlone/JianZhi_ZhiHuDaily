package com.jianyiclub.jianzhi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by wl624 on 2017/11/14.
 */

public class MySplashLayout extends RelativeLayout {
    private int width,height;
    public MySplashLayout(Context context) {
        super(context);
    }

    public MySplashLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySplashLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    {
        width=getWidth();
        height=getHeight();
    }


}
