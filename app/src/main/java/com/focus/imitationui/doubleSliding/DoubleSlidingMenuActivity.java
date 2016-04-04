package com.focus.imitationui.doubleSliding;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.focus.imitationui.R;
import com.focus.imitationui.doubleSliding.MyDrawlerLayout;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;

public class DoubleSlidingMenuActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
    private Toolbar mToolbar;
    private MyDrawlerLayout mDrawerLayout;
    private int mBaseTranslationY;
    private ObservableListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_sliding);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        initView();
    }

    /**
     * 右滑按键的点击事件
     * @param view
     */
    public void OpenRightMenu(View view) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mDrawerLayout = (MyDrawlerLayout ) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        mDrawerLayout.setDrawerListener(new MyDrawerListener());
        mListView = (ObservableListView) findViewById(R.id.list);
        mListView.setScrollViewCallbacks(this);

        // TODO These are dummy. Populate your data here.
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            items.add("Item " + i);
        }
        mListView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        Log.d("tag", "scrollY>>>>" + scrollY);

        int toolbarHeight = mToolbar.getHeight();
        if (dragging || scrollY < toolbarHeight) {
            if (firstScroll) {
                float currentHeaderTranslationY = ViewHelper.getTranslationY(mToolbar);
                if (-toolbarHeight < currentHeaderTranslationY && toolbarHeight < scrollY) {
                    mBaseTranslationY = scrollY;
                }
            }
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            ViewPropertyAnimator.animate(mToolbar).cancel();
            ViewHelper.setTranslationY(mToolbar, headerTranslationY);
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }


    /**
     * DrawerLayout事件监听
     */
    private class MyDrawerListener implements DrawerListener {
        @Override
        public void onDrawerStateChanged(int newState) {
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
                /*设置动画效果*/
            View mContent = mDrawerLayout.getChildAt(0);
            View mMenu = drawerView;
            float scale = 1 - slideOffset;
            float rightScale = 0.8f + scale * 0.2f;

            if (drawerView.getTag().equals("LEFT")) {

                float leftScale = 1 - 0.3f * scale;

                ViewHelper.setScaleX(mMenu, leftScale);
                ViewHelper.setScaleY(mMenu, leftScale);
                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                ViewHelper.setTranslationX(mContent,
                        mMenu.getMeasuredWidth() * (1 - scale));
                ViewHelper.setPivotX(mContent, 0);
                ViewHelper.setPivotY(mContent,
                        mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);
            } else {
                ViewHelper.setTranslationX(mContent,
                        -mMenu.getMeasuredWidth() * slideOffset);
                ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                ViewHelper.setPivotY(mContent,
                        mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);
            }

        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerLayout.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        }
    }
}
