package com.wanliyang.wanliyang.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.wanliyang.wanliyang.R;
import com.wanliyang.wanliyang.adapter.ViewPagerAdapter;
import com.wanliyang.wanliyang.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public class TypeFragment extends BaseFragment {
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    private ViewPagerAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void intiData() {
        super.intiData();
        Log.e("TAG", "类型页面初始化完成");
        adapter = new ViewPagerAdapter(mContext);
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
