package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.ActionMessageEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class ActionMessageAdapter extends RecyclerView.Adapter<ActionMessageAdapter.ViewHolder> {
    private final Context mContext;

    private List<ActionMessageEvent> mItemList = new ArrayList<ActionMessageEvent>();

    public ActionMessageAdapter(Context mContext, List<ActionMessageEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<ActionMessageEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public ActionMessageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.action_message_item, parent, false);
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
        return 14;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_message)
        ImageView ivMessage;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_title1)
        TextView tvTitle1;
        @BindView(R.id.tv_title2)
        TextView tvTitle2;
        @BindView(R.id.bt_activity_state)
        Button btActivityState;
        @BindView(R.id.view3)
        View view3;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
