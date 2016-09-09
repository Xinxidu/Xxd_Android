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
import com.xinxidu.xxd.adapter.ProfitSkillAdapter;
import com.xinxidu.xxd.bean.DayInvestmentEvent;
import com.xinxidu.xxd.bean.ProfitSkillEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfitSkillActivity extends AppCompatActivity {
    ProfitSkillAdapter mProfitSkillAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayList<ProfitSkillEvent> mItem;
    private RecyclerView mRecyclerView;

    public static void startProfitSkillActivity(Context context) {
        Intent intent = new Intent(context, ProfitSkillActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_skill);
        ButterKnife.bind(this);
        tvTitle.setText("盈利技巧");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<ProfitSkillEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mProfitSkillAdapter = new ProfitSkillAdapter(this);
        mRecyclerView.setAdapter(mProfitSkillAdapter);
        mProfitSkillAdapter.setData(mItem);
        mProfitSkillAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
