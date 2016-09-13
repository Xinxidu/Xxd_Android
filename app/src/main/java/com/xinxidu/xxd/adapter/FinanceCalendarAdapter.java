package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.FinanceCalendarEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class FinanceCalendarAdapter extends RecyclerView.Adapter<FinanceCalendarAdapter.ViewHolder> {
    private final Context mContext;

    private List<FinanceCalendarEvent> mItemList = new ArrayList<FinanceCalendarEvent>();

    public FinanceCalendarAdapter(Context mContext, List<FinanceCalendarEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<FinanceCalendarEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public FinanceCalendarAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.finance_calence_item, parent, false);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_activity_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TaskDetailsActivity.startTaskDetailsActivity(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 13;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_country_icon)
        ImageView ivCountryIcon;
        @BindView(R.id.tv_country)
        TextView tvCountry;
        @BindView(R.id.tv_index)
        TextView tvIndex;
        @BindView(R.id.tv_top_value)
        TextView tvTopValue;
        @BindView(R.id.tv_forecast)
        TextView tvForecast;
        @BindView(R.id.tv_actual)
        TextView tvActual;
        @BindView(R.id.tv_top_value_num)
        TextView tvTopValueNum;
        @BindView(R.id.tv_forecast_num)
        TextView tvForecastNum;
        @BindView(R.id.tv_actual_num)
        TextView tvActualNum;
        @BindView(R.id.relative)
        RelativeLayout relative;

        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
