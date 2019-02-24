package com.jianyiclub.jianzhi.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.*;

import com.jianyiclub.jianzhi.utils.DpToPx;

/**
 * Created by wl624 on 2017/11/14.
 */

public class ThemeLocation extends android.view.View {
    private Path path=new Path();
    public ThemeLocation(Context context) {
        super(context);
    }

    public ThemeLocation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThemeLocation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(0x00000000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(0xff5ec8c4);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path.moveTo(getWidth()/2,getHeight()/2-16);
        path.lineTo((float) (getWidth()/2+1.7*20),getHeight()/2);
        path.lineTo(getWidth()/2,getHeight()/2+16);
        path.close();
        canvas.drawPath(path,paint);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/9,paint);

    }
}
