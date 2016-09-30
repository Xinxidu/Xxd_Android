package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.EntrustItemEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class EntrustItemAdapter extends RecyclerView.Adapter<EntrustItemAdapter.ViewHolder> {
    private final Context mContext;

    private List<EntrustItemEvent> mItemList = new ArrayList<EntrustItemEvent>();

    public EntrustItemAdapter(Context mContext, List<EntrustItemEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<EntrustItemEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public EntrustItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.entrust_adapter_item, parent, false);
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
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_buy)
        TextView tvBuy;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_deal)
        TextView tvDeal;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.ll_trade_item)
        LinearLayout llTradeItem;

        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
