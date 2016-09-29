package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.EntrustItemAdapter;
import com.xinxidu.xxd.event.EntrustItemEvent;
import com.xinxidu.xxd.utils.TimeDialogUtils;
import com.xinxidu.xxd.view.ChangeDatePopwindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/9.
 */
public class OrderHistoryAdjustActivity extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_ok)
    TextView tvTitleOk;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.rl_adjust)
    LinearLayout rlAdjust;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    EntrustItemAdapter mEntrustItemAdapter;

    private ArrayList<EntrustItemEvent> mItem;
    private RecyclerView mRecyclerView;

    public static void startOrderHistoryAdjustActivity(Context context) {
        Intent intent = new Intent(context, OrderHistoryAdjustActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history_adjust_activity);
        ButterKnife.bind(this);
        tvTitle.setText("历史调期单");
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

    @OnClick({R.id.back, R.id.tv_start, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_start:
                selectDate(tvStart);
                break;
            case R.id.tv_end:
                selectDate(tvEnd);
                break;
        }
    }

    private String[] selectDate(final TextView time) {
        final String[] str = new String[10];
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(this);
        mChangeBirthDialog.showAtLocation(time, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
                time.setText(year + "-" + month + "-" + day);

            }
        });
        return str;
    }
}
