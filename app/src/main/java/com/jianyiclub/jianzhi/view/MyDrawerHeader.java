package com.jianyiclub.jianzhi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jianyiclub.jianzhi.R;

/**
 * Created by wl624 on 2017/11/10.
 */

public class MyDrawerHeader extends View {

    private boolean bo=true;
    private Context context;
    Bitmap bitmap;
    public MyDrawerHeader(Context context) {
        super(context);
        this.context=context;
    }

    public MyDrawerHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyDrawerHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=getWidth();
        int y=getHeight();



        canvas.save();
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF6bd7f2);
        paint.setAntiAlias(true);
        canvas.drawRect(0,0,x,y,paint);
        canvas.restore();
        canvas.save();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon);
        paint.setColor(0xFF000000);

        System.out.println(bitmap.getWidth()+"  "+bitmap.getHeight());
        canvas.scale(bitmap.getWidth()/context.getResources().getDisplayMetrics().density/400,
                bitmap.getHeight()/context.getResources().getDisplayMetrics().density/400,x/2,y/2);
        canvas.drawBitmap(bitmap,x/2-bitmap.getWidth()/2-50*context.getResources().getDisplayMetrics().density,y/2-bitmap.getHeight()/2,paint);
        canvas.restore();
        canvas.save();
        if(!getBG()){

            paint.setColor(0x80000000);
            canvas.drawRect(0,0,x,y,paint);
        }else {

        }
        canvas.restore();

    }
    //bo=true为日间，false为夜间
    public void setBG(boolean bo){
        this.bo=bo;
        invalidate();
    }
    public boolean getBG(){
        return bo;
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
