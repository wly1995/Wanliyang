package com.wanliyang.wanliyang.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wanliyang.wanliyang.R;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public class ViewPagerAdapter extends PagerAdapter {
    int[] ns = {R.drawable.meinv ,R.drawable.meinv1};
    String[] titles = {"性感","诱惑"};
    private final Context mContext;
    private ImageView ivItem;

    public ViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager, null);
        container.addView(view);
//        ButterKnife.inject(container);
        ivItem = (ImageView) view.findViewById(R.id.iv_item);
        ivItem.setImageResource(ns[position]);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
