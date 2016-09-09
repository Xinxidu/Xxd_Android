package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.DayInvestmentAdapter;
import com.xinxidu.xxd.adapter.LiveRoomAdapter;
import com.xinxidu.xxd.bean.DayInvestmentEvent;
import com.xinxidu.xxd.bean.LiveRoomEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayInvestmentActivity extends AppCompatActivity {

    DayInvestmentAdapter mDayInvestmentAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ArrayList<DayInvestmentEvent> mItem;
    private RecyclerView mRecyclerView;

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
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<DayInvestmentEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mDayInvestmentAdapter = new DayInvestmentAdapter(this);
        mRecyclerView.setAdapter(mDayInvestmentAdapter);
        mDayInvestmentAdapter.setData(mItem);
        mDayInvestmentAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
