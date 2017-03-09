package com.wanliyang.wanliyang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wanliyang.wanliyang.R;
import com.wanliyang.wanliyang.bean.HomeBean;
import com.wanliyang.wanliyang.utils.Constants;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewholder> {
    private final Context mContext;
    private final List<HomeBean.DataBean.NewsBean> news;


    public HomeAdapter(Context mContext, List<HomeBean.DataBean.NewsBean> news) {
        this.mContext = mContext;
        this.news = news;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(View.inflate(mContext, R.layout.fragement_home_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.setData(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        public MyViewholder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void setData(HomeBean.DataBean.NewsBean newsBean) {
            tvTitle.setText(newsBean.getTitle());
            tvTime.setText(newsBean.getPubdate());
            //加载图片
            Glide.with(mContext).load(Constants.BASE_URL+newsBean.getListimage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.news_pic_default)
                    .error(R.drawable.news_pic_default)
                    .into(ivIcon);
        }
    }
}
