package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.HotActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class HotActivityAdapter extends RecyclerView.Adapter<HotActivityAdapter.ViewHolder> {
    private final Context mContext;

    private List<HotActivityEvent> mItemList = new ArrayList<HotActivityEvent>();

    public HotActivityAdapter(Context mContext, List<HotActivityEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<HotActivityEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public HotActivityAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_activity_item, parent, false);
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
        return 6;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.iv_activity_icon)
        ImageView ivActivityIcon;
        @BindView(R.id.textView1)
        TextView textView1;
        @BindView(R.id.tv_activity_start_time)
        TextView tvActivityStartTime;
        @BindView(R.id.tv_start_end_time)
        TextView tvStartEndTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
