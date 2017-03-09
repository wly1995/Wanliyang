package com.wanliyang.wanliyang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public abstract class BaseFragment extends Fragment {
    public Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        return initView();
    }

    /**
     * 初始化视图，由每个子类具体实现
     * @return
     */
    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intiData();
    }

    /**
     * 初始化数据
     */
    public void intiData() {

    }
}
