package com.egco428.singletouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 6272user on 10/27/2016 AD.
 */
public class SingleTouchEventView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private float eventX;
    private float eventY;
    private boolean fingerDown = false;

    public SingleTouchEventView(Context context, AttributeSet attrs){
        super(context,attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND); //make stroke smooth
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPath(path,paint);
        if (fingerDown){
            canvas.drawCircle(eventX,eventY,20,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        eventX = event.getX();
        eventY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                fingerDown = true;
                path.moveTo(eventX,eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX,eventY);
                break;
            case MotionEvent.ACTION_UP:
                fingerDown = false;
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
