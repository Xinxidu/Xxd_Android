package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class BroadcastNewsItemAdapter extends RecyclerView.Adapter<BroadcastNewsItemAdapter.ViewHolder> {
    private final Context mContext;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_up_where)
    TextView tvUpWhere;
    @BindView(R.id.view)
    View view;

    private List<BroadcastNewsItemEvent> mItemList = new ArrayList<BroadcastNewsItemEvent>();

    public BroadcastNewsItemAdapter(Context mContext, List<BroadcastNewsItemEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<BroadcastNewsItemEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public BroadcastNewsItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.broadcast_news_item, parent, false);
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

        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
