package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.BroadcastNewsBase2Adapter;
import com.xinxidu.xxd.adapter.BroadcastNewsItemAdapter;
import com.xinxidu.xxd.event.BroadcastNewsItemEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/30.
 */
public class CollectionActivity extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startCollectionActivity(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    BroadcastNewsBase2Adapter mBroadcastNewsBase2Adapter;

    private ArrayList<BroadcastNewsItemEvent> mItem;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colection_news_activity);
        ButterKnife.bind(this);
        tvTitle.setText("收藏");
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<BroadcastNewsItemEvent>();
        mItem.add(null);

        mBroadcastNewsBase2Adapter = new BroadcastNewsBase2Adapter(this);
        mRecyclerView.setAdapter(mBroadcastNewsBase2Adapter);
        mBroadcastNewsBase2Adapter.setData(mItem);
        mBroadcastNewsBase2Adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
