package com.xinxidu.xxd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.XiduNewsAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.event.XiduNewsEvent;
import com.xinxidu.xxd.netWork.ProfitSkillBean;
import com.xinxidu.xxd.netWork.XiduNewsBean;
import com.xinxidu.xxd.utils.pull.SwipyRefreshLayout;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.http.Query;

public class XiduNewsActivity extends Fragment {
    XiduNewsAdapter mXiduNewsAdapter;
    private ArrayList<XiduNewsBean.ResultListBean> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout swipe_refresh;
    private static final int TOP_REFRESH = 1;
    private static final int BOTTOM_REFRESH = 2;
    private int pageIndex = 1;
    //得到最大条目，判断是否还有加载
    private int sumPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_xidu_news, container, false);
        ButterKnife.bind(this, view);
        //获取控件id
        initView(view);
        //创建适配器，并设置加载更多监听
        initAdapter();
        //网络请求
        webRequest(pageIndex);
        //刷新
        refresh();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_xidunews);
        swipe_refresh = (SwipyRefreshLayout) view.findViewById(R.id.swipe_xidunews);
    }

    private void initAdapter() {
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mXiduNewsAdapter = new XiduNewsAdapter(getActivity(), mItem);
        mRecyclerView.setAdapter(mXiduNewsAdapter);
    }

    private void webRequest(int pageIndex) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Compares.URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();
        XiduNewsActivity.Api api = retrofit.create(XiduNewsActivity.Api.class);
        retrofit2.Call<XiduNewsBean> data = api.getData(".guanwang", "gonggao", pageIndex, 10);
        data.enqueue(new retrofit2.Callback<XiduNewsBean>() {
            @Override
            public void onResponse(retrofit2.Call<XiduNewsBean> call, retrofit2.Response<XiduNewsBean> response) {
                sumPage = response.body().sumPage;
                mItem.addAll(response.body().data);
                mXiduNewsAdapter.notifyDataSetChanged();
                swipe_refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(retrofit2.Call<XiduNewsBean> call, Throwable t) {
                Toast.makeText(getActivity(), "加载失败,请重试", Toast.LENGTH_SHORT).show();
                swipe_refresh.setRefreshing(false);
            }
        });
    }

    private void refresh() {
        //设置刷新颜色
        swipe_refresh.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent, R.color.primary_text);
        swipe_refresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                dataOption(TOP_REFRESH);
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoad(int index) {
                dataOption(BOTTOM_REFRESH);
                if (mItem.size() == sumPage) {
                    Toast.makeText(getActivity(), "没有数据了", Toast.LENGTH_SHORT).show();
                    swipe_refresh.setRefreshing(false);
                } else {
                    Toast.makeText(getActivity(), "加载中,请稍后...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataOption(int option) {
        switch (option) {
            case TOP_REFRESH:
                //下拉刷新
                mItem.clear();
                webRequest(1);
                break;
            case BOTTOM_REFRESH:
                //上拉加载更多
                pageIndex++;
                webRequest(pageIndex);
                break;
        }
    }

    interface Api {
        @retrofit2.http.GET("/ZhuBan/")
        retrofit2.Call<XiduNewsBean> getData(@Query("type") String type, @Query("defference") String defference, @Query("indexPage") int indexPage, @Query("pageRows") int pageRows);
    }


}
