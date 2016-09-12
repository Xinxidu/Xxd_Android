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
import com.xinxidu.xxd.adapter.ActionMessageAdapter;
import com.xinxidu.xxd.adapter.ProfitSkillAdapter;
import com.xinxidu.xxd.bean.ActionMessageEvent;
import com.xinxidu.xxd.bean.ProfitSkillEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtionMessageActivity extends AppCompatActivity {
    ActionMessageAdapter mActionMessageAdapter;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayList<ActionMessageEvent> mItem;
    private RecyclerView mRecyclerView;

    public static void startAtionMessageActivity(Context context) {
        Intent intent = new Intent(context, AtionMessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ation_message);
        ButterKnife.bind(this);
        tvTitle.setText("活动消息");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<ActionMessageEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mActionMessageAdapter = new ActionMessageAdapter(this);
        mRecyclerView.setAdapter(mActionMessageAdapter);
        mActionMessageAdapter.setData(mItem);
        mActionMessageAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
