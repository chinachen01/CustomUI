package com.focus.imitationui.customPopupWindow;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 2016/4/4.
 */
public class PopupViewPagerAdapter extends PagerAdapter {
    private List<View> mDatas = new ArrayList<>();
    public void setDatas(List<View> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDatas.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDatas.get(position));
        return mDatas.get(position);
    }
}
