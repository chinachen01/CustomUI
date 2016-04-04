package com.focus.imitationui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.focus.imitationui.customPopupWindow.PopupWindowActivity;
import com.focus.imitationui.doubleSliding.DoubleSlidingMenuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 2016/4/4.
 */
public class MainActivity extends AppCompatActivity {
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        mList = (ListView) findViewById(R.id.main_list);
        MainAdapter mainAdapter = new MainAdapter(this);
        mList.setAdapter(mainAdapter);
        mainAdapter.setData(getAdapterData());
        mList.setOnItemClickListener(new MainOnItemClickListener());
    }

    private List<String> getAdapterData() {
        List<String> datas = new ArrayList<>();
        datas.add("double sliding menu");
        datas.add("custom popup window");
        return datas;
    }

    private class MainOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(MainActivity.this, DoubleSlidingMenuActivity.class);
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, PopupWindowActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    private class MainAdapter extends BaseAdapter {
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;
        private LayoutInflater mInflater;

        private MainAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        public void setData(List<String> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            text.setText(mDatas.get(position));
            return convertView;
        }
    }
}
