package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.EntrustItemAdapter;
import com.xinxidu.xxd.event.EntrustItemEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodayDecideListActivity extends AppCompatActivity {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    EntrustItemAdapter mEntrustItemAdapter;

    private ArrayList<EntrustItemEvent> mItem;
    private RecyclerView mRecyclerView;

    public static void startTodayDecideListActivity(Context context) {
        Intent intent = new Intent(context, TodayDecideListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_decide_list);
        ButterKnife.bind(this);
        tvTitle.setText("当日订立单");
        initRecycler();
    }

    private void initRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<EntrustItemEvent>();
        mItem.add(null);

        mEntrustItemAdapter = new EntrustItemAdapter(this);
        mRecyclerView.setAdapter(mEntrustItemAdapter);
        mEntrustItemAdapter.setData(mItem);
        mEntrustItemAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
