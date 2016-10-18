package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.DayInvestmentAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.netWork.DayInvestmentBean;
import com.xinxidu.xxd.utils.pull.SwipyRefreshLayout;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Query;

public class DayInvestmentActivity extends Activity {

    DayInvestmentAdapter mDayInvestmentAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ArrayList<DayInvestmentBean.ResultListBean> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout swipe_refresh;
    private static final int TOP_REFRESH = 1;
    private static final int BOTTOM_REFRESH = 2;
    private int pageIndex = 1;
    //得到最大条目，判断是否还有加载
    private int sumPage;

    public static void startDayInvestmentActivity(Context context) {
        Intent intent = new Intent(context, DayInvestmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_investment);
        ButterKnife.bind(this);
        tvTitle.setText("每日投资策略");
        //获取控件id
        initView();
        //创建适配器，并设置加载更多监听
        initAdapter();
        //网络请求
        webRequest(pageIndex);
        //刷新
        refresh();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_invest);
        swipe_refresh = (SwipyRefreshLayout) findViewById(R.id.swipe_invest);
    }
    private void initAdapter(){
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDayInvestmentAdapter = new DayInvestmentAdapter(this, mItem);
        mDayInvestmentAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mDayInvestmentAdapter);
    }
    private void webRequest(int pageIndex){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Compares.URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<DayInvestmentBean> data = api.getData(".guanwang", "hangqing", pageIndex, 10);
        data.enqueue(new Callback<DayInvestmentBean>() {
            @Override
            public void onResponse(Call<DayInvestmentBean> call, Response<DayInvestmentBean> response) {
                sumPage = response.body().sumPage;
                mItem.addAll(response.body().data);
                mDayInvestmentAdapter.notifyDataSetChanged();
                swipe_refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<DayInvestmentBean> call, Throwable t) {
                Toast.makeText(DayInvestmentActivity.this, "加载失败,请重试", Toast.LENGTH_SHORT).show();
                swipe_refresh.setRefreshing(false);
            }
        });
    }
    private void refresh(){
        //设置刷新颜色
        swipe_refresh.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent, R.color.primary_text);
        swipe_refresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                dataOption(TOP_REFRESH);
                Toast.makeText(DayInvestmentActivity.this, "刷新完毕", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoad(int index) {
                dataOption(BOTTOM_REFRESH);
                if (mItem.size() == sumPage) {
                    Toast.makeText(DayInvestmentActivity.this, "没有数据了", Toast.LENGTH_SHORT).show();
                    swipe_refresh.setRefreshing(false);
                } else {
                    Toast.makeText(DayInvestmentActivity.this, "加载中,请稍后...", Toast.LENGTH_SHORT).show();
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

    DayInvestmentAdapter.OnItemClickListener mOnItemClickListener = new DayInvestmentAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(DayInvestmentActivity.this, DayInvestmentDetailActivity.class);
            intent.putExtra("id", mItem.get(position).Id+"");//转成String不然拿不到
            startActivity(intent);
        }
    };
    interface Api {
       @retrofit2.http.GET("/ZhuBan/")
       Call<DayInvestmentBean> getData(@Query("type") String type, @Query("defference") String defference, @Query("indexPage") int indexPage, @Query("pageRows") int pageRows);
    }
    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
