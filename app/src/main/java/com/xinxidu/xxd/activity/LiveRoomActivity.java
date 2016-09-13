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
import com.xinxidu.xxd.adapter.LiveRoomAdapter;
import com.xinxidu.xxd.event.LiveRoomEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveRoomActivity extends AppCompatActivity {

    LiveRoomAdapter mLiveRoomAdapter;

    private ArrayList<LiveRoomEvent> mItem;
    private RecyclerView mRecyclerView;


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startLiveRoomActivity(Context context) {
        Intent intent = new Intent(context, LiveRoomActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room);
        ButterKnife.bind(this);
        tvTitle.setText("直播间");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<LiveRoomEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mLiveRoomAdapter = new LiveRoomAdapter(this);
        mRecyclerView.setAdapter(mLiveRoomAdapter);
        mLiveRoomAdapter.setData(mItem);
        mLiveRoomAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
