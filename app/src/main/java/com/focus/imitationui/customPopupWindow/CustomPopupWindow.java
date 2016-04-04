package com.focus.imitationui.customPopupWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.focus.imitationui.R;
import com.focus.imitationui.utils.ScreenUtils;

import java.util.ArrayList;


public class CustomPopupWindow extends PopupWindow {
    private View contentView;
    private Button mDismissBtn;
    private ViewPager mViewPager;
    private Context mContext;

    public CustomPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        this.contentView = contentView;
        mContext = contentView.getContext();
        mDismissBtn = (Button) contentView.findViewById(R.id.popup_window_dismiss_btn);
        mViewPager = (ViewPager) contentView.findViewById(R.id.popup_window_view_pager);
        initViewPager();
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        /* 设置一个半透明背景,点击back或其他地方才能取消弹窗*/
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        /* 设置动画效果 */
        this.setAnimationStyle(R.style.AnimationPreView);
                /* 设置监听事件 */
        mDismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopupWindow.this.dismiss();
            }
        });
    }

    private void initViewPager() {
        ArrayList<View> datas = new ArrayList<>();
        ImageView image = null;
        image = new ImageView(mContext);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image.setImageResource(R.drawable.image1);
        datas.add(image);
        image = new ImageView(mContext);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image.setImageResource(R.drawable.image2);
        datas.add(image);
        image = new ImageView(mContext);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image.setImageResource(R.drawable.image3);
        datas.add(image);
        PopupViewPagerAdapter adapter = new PopupViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        adapter.setDatas(datas);
    }

    /**
     * 默认全屏
     *
     * @param contentView
     */
    public CustomPopupWindow(View contentView) {
        this(contentView, ScreenUtils.getScreenWidth(contentView.getContext()), ScreenUtils.getScreenHeight(contentView.getContext()));
    }
}
