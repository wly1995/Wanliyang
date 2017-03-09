package com.wanliyang.wanliyang.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wanliyang.wanliyang.base.BaseFragment;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public class TypeFragment extends BaseFragment {
    private TextView textView ;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
    @Override
    public void intiData() {
        super.intiData();
        Log.e("TAG","类型页面初始化完成");
        textView.setText("类型页面");
    }
}
