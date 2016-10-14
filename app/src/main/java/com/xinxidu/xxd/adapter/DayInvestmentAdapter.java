package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.event.DayInvestmentEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by limingquan on 2016/9/5.
 */
public class DayInvestmentAdapter extends RecyclerView.Adapter<DayInvestmentAdapter.ViewHolder> {
    private final Context mContext;
    private final List<DayInvestmentEvent> mItemList;
    private OnItemClickListener mOnItemClickListener;

    public DayInvestmentAdapter(Context mContext, List<DayInvestmentEvent> itemList) {
        this.mItemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.day_investment_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(mContext).load(Compares.URL+mItemList.get(position).getLitpic()).centerCrop().into(holder.imageView);
        holder.tvTitle.setText(mItemList.get(position).getTitle());
        holder.tvBody.setText(mItemList.get(position).getBody());
        holder.tvTime.setText(mItemList.get(position).getPubdate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.imageView, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size() == 0 ? 0 : mItemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
//        @BindView(R.id.image)
//        ImageView image;
        @BindView(R.id.relative)
        RelativeLayout relative;
        @BindView(R.id.tv_title)
        TextView tvTitle;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
