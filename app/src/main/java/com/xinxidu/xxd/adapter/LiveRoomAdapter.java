package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.LiveRoomEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class LiveRoomAdapter extends RecyclerView.Adapter<LiveRoomAdapter.ViewHolder> {
    private final Context mContext;
    private List<LiveRoomEvent> mItemList = new ArrayList<LiveRoomEvent>();

    public LiveRoomAdapter(Context mContext, List<LiveRoomEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<LiveRoomEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public LiveRoomAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.live_room_item, parent, false);
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
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.relative)
        RelativeLayout relative;
        @BindView(R.id.textView10)
        TextView textView10;
        @BindView(R.id.bt_live_telecast)
        Button btLiveTelecast;
        @BindView(R.id.textView11)
        TextView textView11;
        @BindView(R.id.textView13)
        TextView textView13;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
