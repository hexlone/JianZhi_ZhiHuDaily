package com.jianyiclub.jianzhi.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.*;

import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.utils.DpToPx;

/**
 * Created by wl624 on 2017/11/16.
 */

public class MyButton extends android.view.View {
    private OnUpActionListener onUpActionListener=null;
    private Context context;
    private float eventX,eventY;
    private float progress=0;
    private boolean flag=false;
    public MyButton(Context context) {
        super(context);
        this.context=context;
    }

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Config.isNight?0xff454545:0xffffffff);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.save();
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),DpToPx.dpToPx(context,2),DpToPx.dpToPx(context,2),paint);
        canvas.restore();
        canvas.save();
        paint.setTextSize(DpToPx.dpToPx(context,18));
        paint.setColor(Config.isNight?0xffffffff:0xff000000);
        canvas.drawText("夜间模式",DpToPx.dpToPx(context,10),getHeight()/2+DpToPx.dpToPx(context,18)/2-DpToPx.dpToPx(context,2),paint);
        canvas.restore();
        canvas.save();
        paint.setColor(Config.isNight?0xff67b2ff:0xffcccccc);
        canvas.drawRoundRect(getWidth()/2+DpToPx.dpToPx(context,25),2*getHeight()/5,getWidth()-DpToPx.dpToPx(context,25),3*getHeight()/5,getHeight()/10,getHeight()/10,paint);
        canvas.restore();
        canvas.save();
        paint.setColor(Config.isNight?0xff0f95ea:0xffa8a8a8);
        paint.setStyle(Paint.Style.FILL);

        if(!flag){
            if(!Config.isNight){
                canvas.drawCircle(getWidth()/2+DpToPx.dpToPx(context,25)+progress,getHeight()/2,DpToPx.dpToPx(context,9),paint);

            }else {
                canvas.drawCircle(getWidth()-DpToPx.dpToPx(context,25)-progress,getHeight()/2,DpToPx.dpToPx(context,9),paint);
            }
        }else {
            if(Config.isNight){
                canvas.drawCircle(getWidth()/2+DpToPx.dpToPx(context,25)+progress,getHeight()/2,DpToPx.dpToPx(context,9),paint);

            }else {
                canvas.drawCircle(getWidth()-DpToPx.dpToPx(context,25)-progress,getHeight()/2,DpToPx.dpToPx(context,9),paint);
            }
        }
        canvas.restore();



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                eventX=event.getX();
                eventY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if(eventX<DpToPx.dpToPx(context,137)&&eventX>DpToPx.dpToPx(context,87)&&eventY<DpToPx.dpToPx(context,40)&&eventY>DpToPx.dpToPx(context,13)){
                    if(onUpActionListener!=null){
                        onUpActionListener.OnUp(eventX,eventY,getWidth()/2-DpToPx.dpToPx(context,50));
                    }
                }
            default:break;

        }
        return true;
    }

    public void setOnUpActionListener(OnUpActionListener up) {
        onUpActionListener = up;
    }
    public interface OnUpActionListener {
        public void OnUp(float x, float y,float way);
    }
    public void setProgress(float progress){
        this.progress=progress;
        flag=true;
        invalidate();

    }
}
