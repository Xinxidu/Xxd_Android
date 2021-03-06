package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.BroadcastNewsItemEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class BroadcastNewsBase2Adapter extends RecyclerView.Adapter<BroadcastNewsBase2Adapter.ViewHolder> {
    private final Context mContext;

    private List<BroadcastNewsItemEvent> mItemList = new ArrayList<BroadcastNewsItemEvent>();

    public BroadcastNewsBase2Adapter(Context mContext, List<BroadcastNewsItemEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<BroadcastNewsItemEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public BroadcastNewsBase2Adapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.broadcast_news_base2_item, parent, false);
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
        return 15;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_body)
        TextView tvBody;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_live1)
        RelativeLayout rlLive1;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
