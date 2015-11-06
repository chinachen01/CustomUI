package com.focus.imitationui;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;

/**
 * Created by chenyong on 2015/11/5.
 */
public class MyDrawlerLayout extends DrawerLayout {
    private float mLastX;
    public MyDrawlerLayout(Context context) {
        super(context);
    }

    public MyDrawlerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawlerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重新onTouchEvent,增加触发距离
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                /* 当手指按下的点位置处于屏幕左边,且手指滑动的距离大于1/8个屏幕时触发 */
                if (ev.getX() - mLastX > getWidth() / 8 && mLastX <getWidth()/2) {
                    openDrawer(Gravity.LEFT);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
