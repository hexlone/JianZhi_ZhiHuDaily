package com.jianyiclub.jianzhi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jianyiclub.jianzhi.utils.DpToPx;

/**
 * Created by wl624 on 2017/11/15.
 */

public class MyView extends View {
    private float progress=0,pro=0;
    private int flag;
    private Context context;
    public MyView(Context context) {
        super(context);
        this.context=context;
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Path path=new Path();
        if(flag==0){
            if(progress<= DpToPx.dpToPx(context,8)){
                path.moveTo(getWidth()/2-DpToPx.dpToPx(context,8)+progress,getHeight()/2-DpToPx.dpToPx(context,4)+progress+progress/2);
                path.lineTo(getWidth()/2,getHeight()/2+DpToPx.dpToPx(context,4)+progress/2);
                path.lineTo(getWidth()/2+DpToPx.dpToPx(context,8),getHeight()/2-DpToPx.dpToPx(context,4)+progress/2);
                path.lineTo(getWidth()/2+DpToPx.dpToPx(context,8)-progress,getHeight()/2-DpToPx.dpToPx(context,4)-progress+progress/2);
            }else {
                path.moveTo(getWidth()/2+progress-DpToPx.dpToPx(context,8),getHeight()/2+DpToPx.dpToPx(context,16)-progress+(progress-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2+DpToPx.dpToPx(context,8),getHeight()/2+(progress-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2,getHeight()/2-DpToPx.dpToPx(context,8)+(progress-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2-progress+DpToPx.dpToPx(context,8),getHeight()/2-DpToPx.dpToPx(context,16)+progress+(progress-DpToPx.dpToPx(context,8))/2);
            }
        }else {
            if(pro<=DpToPx.dpToPx(context,8)){
                path.moveTo(getWidth()/2+DpToPx.dpToPx(context,8)-pro,getHeight()/2+DpToPx.dpToPx(context,4)-pro-pro/2);
                path.lineTo(getWidth()/2,getHeight()/2-DpToPx.dpToPx(context,4)-pro/2);
                path.lineTo(getWidth()/2-DpToPx.dpToPx(context,8),getHeight()/2+DpToPx.dpToPx(context,4)-pro/2);
                path.lineTo(getWidth()/2-DpToPx.dpToPx(context,8)+pro,getHeight()/2+DpToPx.dpToPx(context,4)+pro-pro/2);
            }else {
                path.moveTo(getWidth()/2-pro+DpToPx.dpToPx(context,8),getHeight()/2-DpToPx.dpToPx(context,16)+pro-(pro-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2-DpToPx.dpToPx(context,8),getHeight()/2-(pro-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2,getHeight()/2+DpToPx.dpToPx(context,8)-(pro-DpToPx.dpToPx(context,8))/2);
                path.lineTo(getWidth()/2+pro-DpToPx.dpToPx(context,8),getHeight()/2-pro+DpToPx.dpToPx(context,16)-(pro-DpToPx.dpToPx(context,8))/2);
            }
        }






        canvas.save();
        canvas.drawPath(path,paint);
        canvas.restore();

    }

    public void setProgress(float progress){
        flag=0;
        this.progress=progress;
        //System.out.println(progress);
        invalidate();
    }
    public void setPro(float pro){
        flag=1;
        this.pro=pro;
        //System.out.println(pro);
        invalidate();
    }

}
