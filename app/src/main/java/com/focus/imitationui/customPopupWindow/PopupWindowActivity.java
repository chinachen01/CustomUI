package com.focus.imitationui.customPopupWindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.focus.imitationui.R;

/**
 * Created by focus on 2016/4/4.
 */
public class PopupWindowActivity extends AppCompatActivity {
    private CustomPopupWindow mPopupWindow;
    private View mParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        mParent = findViewById(R.id.activity_popup_parent);
    }

    public void onClick(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_view, null,false);
        mPopupWindow = new CustomPopupWindow(contentView);
        mPopupWindow.showAtLocation(mParent, Gravity.NO_GRAVITY,0,0);
    }
}
