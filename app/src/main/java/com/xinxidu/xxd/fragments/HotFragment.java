package com.xinxidu.xxd.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.LoginActivity;
import com.xinxidu.xxd.adapter.HotActivityAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.netWork.HotActivityBean;
import com.xinxidu.xxd.utils.pull.SwipyRefreshLayout;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Query;


/**
 * A placeholder fragment containing a simple view.
 */
public class HotFragment extends Fragment {
    private static final int TOP_REFRESH = 1;
    private static final int BOTTOM_REFRESH = 2;
    HotActivityAdapter mHotActivityAdapter;

    private ArrayList<HotActivityBean.ResultListBean> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout swipe_refresh;
    private int pageIndex = 1;
    //得到最大条目，判断是否还有加载是户籍
    private int totalCount;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        //获取控件id
        initView(view);
        //访问网络
        initNet(pageIndex);
        //创建适配器，并设置加载更多监听
        initAdapter();
        //5.0新特性下拉刷新
        setSwipeRefresh();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipe_refresh = (SwipyRefreshLayout) view.findViewById(R.id.swipe_refresh);

    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHotActivityAdapter = new HotActivityAdapter(getActivity(), mItem);
        mRecyclerView.setAdapter(mHotActivityAdapter);

    }

    private void initNet(int pageIndex) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Compares.URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HotActivityBean> data = api.getData("", pageIndex, "");
        data.enqueue(new Callback<HotActivityBean>() {
            @Override
            public void onResponse(Call<HotActivityBean> call, Response<HotActivityBean> response) {
                totalCount = response.body().totalCount;
                mItem.addAll(response.body().resultList);
                mHotActivityAdapter.notifyDataSetChanged();
                swipe_refresh.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<HotActivityBean> call, Throwable t) {
                Toast.makeText(getActivity(), "加载失败,请重试", Toast.LENGTH_SHORT).show();
                swipe_refresh.setRefreshing(false);
            }
        });
    }

    private void setSwipeRefresh() {
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
                if (mItem.size() == totalCount) {
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
                initNet(1);
                break;
            case BOTTOM_REFRESH:
                //上拉加载更多
                pageIndex++;
                initNet(pageIndex);
                break;
        }
    }

    interface Api {
        @retrofit2.http.GET("/app/controller/avtive/query/json/")
        Call<HotActivityBean> getData(@Query("status") String status, @Query("currentPage") int page, @Query("pageSize") String size);
    }

}
