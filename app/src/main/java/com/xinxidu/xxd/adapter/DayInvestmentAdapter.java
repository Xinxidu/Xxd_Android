package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.netWork.DayInvestmentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by limingquan on 2016/9/5.
 */
public class DayInvestmentAdapter extends RecyclerView.Adapter<DayInvestmentAdapter.ViewHolder> {
    private final Context mContext;
    private List<DayInvestmentBean.ResultListBean> mItemList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public DayInvestmentAdapter(Context mContext, List<DayInvestmentBean.ResultListBean> itemList) {
        mItemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.day_investment_item1111, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        Glide.with(mContext).load(Compares.URL+mItemList.get(position).Litpic).centerCrop().into(holder.imageView);
        holder.tvTitle.setText(mItemList.get(position).Title);
        holder.tvContent.setText(mItemList.get(position).Body);
        holder.tvTime.setText(mItemList.get(position).Pubdate);
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.linearlayout, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.imageView)
//        ImageView imageView;
//        @BindView(R.id.relative)
//        RelativeLayout relative;
//        @BindView(R.id.tv_title)
//        TextView tvTitle;
//        @BindView(R.id.tv_body)
//        TextView tvBody;
//        @BindView(R.id.tv_time)
//        TextView tvTime;
//        @BindView(R.id.rl_live1)
//        RelativeLayout rlLive1;
        @BindView(R.id.linearlayout)
        LinearLayout linearlayout;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

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
