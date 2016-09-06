package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.bean.TimeNewsEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class TimeNewsAdapter extends RecyclerView.Adapter<TimeNewsAdapter.ViewHolder> {
    private final Context mContext;

    private List<TimeNewsEvent> mItemList = new ArrayList<TimeNewsEvent>();

    public TimeNewsAdapter(Context mContext, List<TimeNewsEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }
    public void setData(List<TimeNewsEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public TimeNewsAdapter(Context context) {
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.time_new_items, parent, false);
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
        return 16;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.tv_up_where)
        TextView mTvUpWhere;
        LinearLayout mLlListItem;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
