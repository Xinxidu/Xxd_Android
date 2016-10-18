package com.xinxidu.xxd.fragments.marketunscramble;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.TimeNewsEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class MarketUnscrambleAdapter extends RecyclerView.Adapter<MarketUnscrambleAdapter.ViewHolder> {
    private final Context mContext;

    private List<TimeNewsEvent> mItemList = new ArrayList<TimeNewsEvent>();

    public MarketUnscrambleAdapter(Context mContext, List<TimeNewsEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(List<TimeNewsEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public MarketUnscrambleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.day_investment_item1111, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
//        mViewHolder.view.setBackgroundColor(Color.parseColor("#f64d07"));
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
        return 16;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view)
        View view;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.linearlayout)
        LinearLayout linearlayout;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
