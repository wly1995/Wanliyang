package com.wanliyang.wanliyang.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wanliyang.wanliyang.R;
import com.wanliyang.wanliyang.adapter.HomeAdapter;
import com.wanliyang.wanliyang.base.BaseFragment;
import com.wanliyang.wanliyang.bean.HomeBean;
import com.wanliyang.wanliyang.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 万里洋 on 2017/3/9.
 */

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private HomeAdapter adapter;
    /**
     * 获取更多数据的路径
     */
    private String moreUrl;

    private String url = Constants.NEWSCENTER_PAGER_URL;
    /**
     * 是否加载更多
     */
    private boolean isLoadMore = false;
    private List<HomeBean.DataBean.NewsBean> news;
    private List<HomeBean.DataBean.NewsBean> moreNews;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragement_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void intiData() {
        super.intiData();
        Log.e("TAG", "主页面初始化完成");

        getDataFromNet(url);
        //设置监听
        setListenter();

    }

    private void setListenter() {
        //下拉刷新和上拉刷新
        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    private void getDataFromNet(String url) {
        Log.e("TAG",url);
        OkHttpUtils.get().url(url).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", response);
                processData(response);
                refresh.finishRefresh();

            }
        });
    }

    private void processData(String response) {
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
//        Toast.makeText(mContext,homeBean.getData().getNews().get(0).getTitle(),0).show();

        String more = homeBean.getData().getMore();
        Log.e("TAG",more);
        if (TextUtils.isEmpty(more)) {
            moreUrl = "";
        } else{
            moreUrl = Constants.BASE_URL + more;
        }
        if (!isLoadMore){//第一次进来isLoadMore = false;
            news = homeBean.getData().getNews();
            //设置适配器
            adapter = new HomeAdapter(mContext, news);

            rvHome.setAdapter(adapter);
            //设置布局管理   1表示一行只有一个item
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            //设置布局管理器
            rvHome.setLayoutManager(manager);
        }else{//只有在上拉的时候isLoadMore = TRUE;
            //这时更多数据的url不为空，说明有更多列表数据存在
            moreNews = homeBean.getData().getNews();
            //然后把这个列表添加到原来的列表，这就是上拉加载更多的数据
            news.addAll(moreNews);
            //然后刷新适配器，得意更新界面
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isLoadMore = false;
            getDataFromNet(url);
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            if (!TextUtils.isEmpty(moreUrl)) {
                isLoadMore = true;
                getMoreDataFromNet(moreUrl);
            } else{
                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
//                pulltorefreshlistview.onRefreshComplete();
                refresh.finishRefreshLoadMore();
            }
        }

        @Override
        public void onfinish() {
            super.onfinish();
        }
    }

    private void getMoreDataFromNet(String moreUrl) {
        OkHttpUtils.get().url(moreUrl).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", response);
                processData(response);
                refresh.finishRefreshLoadMore();
            }
        });
    }
}
