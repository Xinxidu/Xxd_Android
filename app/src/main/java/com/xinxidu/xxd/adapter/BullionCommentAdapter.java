package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.BullionCommentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class BullionCommentAdapter extends RecyclerView.Adapter<BullionCommentAdapter.ViewHolder> {
    private final Context mContext;

    private List<BullionCommentEvent> mItemList = new ArrayList<BullionCommentEvent>();

    public BullionCommentAdapter(Context mContext, List<BullionCommentEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<BullionCommentEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public BullionCommentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bullion_comment_items, parent, false);
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

        @BindView(R.id.iv_news_image)
        ImageView ivNewsImage;
        @BindView(R.id.tv_news_test)
        TextView tvNewsTest;
        @BindView(R.id.tv_news_time)
        TextView tvNewsTime;
        LinearLayout mLlListItem;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
