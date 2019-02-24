package com.jianyiclub.jianzhi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.*;
import android.view.View;
import android.widget.PopupWindow;

import com.jianyiclub.jianzhi.activity.MainActivity;

/**
 * Created by wl624 on 2017/11/16.
 */

public class MyPop extends PopupWindow {
    private MainActivity mainActivity;
    public MyPop(Context context) {
        super(context);
    }

    public MyPop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyPop() {
        super();
    }

    public MyPop(View contentView) {
        super(contentView);
    }

    public MyPop(int width, int height) {
        super(width, height);
    }

    public MyPop(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public MyPop(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }


    @Override
    public void dismiss() {
        mainActivity=new MainActivity();
        mainActivity.setMy();
        super.dismiss();

    }
}
