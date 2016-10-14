package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.HotActivity;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.event.HotActivityEvent;
import com.xinxidu.xxd.netWork.HotActivityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class HotActivityAdapter extends RecyclerView.Adapter<HotActivityAdapter.ViewHolder> {
    private final Context mContext;
    private List<HotActivityBean.ResultListBean> mItemList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public HotActivityAdapter(Context mContext, List<HotActivityBean.ResultListBean> itemList) {
        mItemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_activity_item1, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(mItemList.get(position).title);
        holder.tvTimeEnd.setText(mItemList.get(position).createDate);
        holder.tvTimeStart.setText(mItemList.get(position).activeTime);
        if (mItemList.get(position).type == 2) {
            holder.tvActivityState.setText("未开始");
            holder.tvActivityState.setBackgroundResource(R.drawable.hot_activity1);
        } else if (mItemList.get(position).type == 1) {
            holder.tvActivityState.setText("开始");
            holder.tvActivityState.setBackgroundResource(R.drawable.hot_activity2);
        } else {
            holder.tvActivityState.setText("结束");
            holder.tvActivityState.setBackgroundResource(R.drawable.hot_activity3);
        }

        //获取图片
        Glide.with(mContext).load(Compares.URL +mItemList.get(position).picUrl).centerCrop().into(holder.ivHotIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
//                Intent intent = new Intent(mContext, HotActivity.class);
//                intent.putExtra("id", mItemList.get(holder.getLayoutPosition()).getId());
//                mContext.startActivity(intent);
                //Toast.makeText(mContext, "id=" + , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.iv_hot_icon)
        ImageView ivHotIcon;
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.relative)
        RelativeLayout relative;
        @BindView(R.id.tv_activity_state)
        TextView tvActivityState;
        @BindView(R.id.tv_time_start)
        TextView tvTimeStart;
        @BindView(R.id.tv_time_end)
        TextView tvTimeEnd;
        @BindView(R.id.linearLayout2)
        LinearLayout linearLayout2;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

}
