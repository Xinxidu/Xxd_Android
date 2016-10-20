package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.xinxidu.xxd.adapter.FinanceCalendarAdapter;
import com.xinxidu.xxd.event.FinanceCalendarEvent;
import com.xinxidu.xxd.utils.calendar.CalendarAdapter;
import com.xinxidu.xxd.utils.calendar.CalenderBean;
import com.xinxidu.xxd.utils.calendar.CalenderDataUtils;
import com.xinxidu.xxd.view.ChangeDatePopwindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinanceCalendarActivity extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_ok)
    TextView tvTitleOk;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_week)
    RelativeLayout llWeek;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerViewItem;

    FinanceCalendarAdapter mFinanceCalendarAdapter;
    private ArrayList<FinanceCalendarEvent> mItem;

    //日历adapter
    private List<CalenderBean> beanList = new ArrayList<>();
    private CalendarAdapter mAdapter;

    public static void startFinanceCalendarActivity(Context context) {
        Intent intent = new Intent(context, FinanceCalendarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_calendar);
        ButterKnife.bind(this);
        tvTitle.setText("财经日历");
        ok.setVisibility(View.VISIBLE);
        ok.setBackgroundResource(R.drawable.calendertime);
        //初始化日历
        initRecyClerView();
        initCalenderData();
        //初始化item
        recyclerViewAdapter();
    }

    private void initRecyClerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new CalendarAdapter(this, beanList);
        //选中颜色
        mAdapter.setOnItemClickLitener(new CalendarAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                List<CalenderBean> data = mAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    CalenderBean calenderBean = data.get(i);
                    if (i == position) {
                        calenderBean.setCurrentItem(true);
                    } else {
                        calenderBean.setCurrentItem(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initCalenderData() {
        List<CalenderBean> dataList = CalenderDataUtils.getCalenderData("2016-10-01", "2016-10-5", "2016-12-20");
        beanList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
        for (int i = 0; i < beanList.size(); i++) {
            CalenderBean calenderBean = beanList.get(i);
            if (calenderBean.getCurrentItem()) {
                mRecyclerView.smoothScrollToPosition(i);
            }
        }
    }

    private void recyclerViewAdapter() {
        recyclerViewItem.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItem.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<FinanceCalendarEvent>();
        mItem.add(null);
        mFinanceCalendarAdapter = new FinanceCalendarAdapter(this);
        recyclerViewItem.setAdapter(mFinanceCalendarAdapter);
        mFinanceCalendarAdapter.setData(mItem);
        mFinanceCalendarAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.back, R.id.ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ok:
                selectDate(tvTitleOk);
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
                str[0] = "" + month + "" + day;
                str[1] = sb.toString();
//                tvData.setText(" " + month + "  " + day);
                tvTitleOk.setText(month);
                tvData.setText(day);
            }
        });
        return str;
    }
}
