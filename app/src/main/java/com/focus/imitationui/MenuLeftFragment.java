package com.focus.imitationui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuLeftFragment extends Fragment
{
    private ImageView mImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_menu, container, false);
        mImage = (ImageView) view.findViewById(R.id.one);
        mImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}