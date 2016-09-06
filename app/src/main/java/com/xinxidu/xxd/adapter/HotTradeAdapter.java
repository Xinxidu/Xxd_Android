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
import com.xinxidu.xxd.bean.HotTradeEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class HotTradeAdapter extends RecyclerView.Adapter<HotTradeAdapter.ViewHolder> {
    private final Context mContext;

    private List<HotTradeEvent> mItemList = new ArrayList<HotTradeEvent>();

    public HotTradeAdapter(Context mContext, List<HotTradeEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<HotTradeEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public HotTradeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_trade_item, parent, false);
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
        @BindView(R.id.tv_silver_water)
        TextView tvSilverWater;
        @BindView(R.id.tv_newest)
        TextView tvNewest;
        @BindView(R.id.tv_business)
        TextView tvBusiness;
        @BindView(R.id.tv_down_up)
        TextView tvDownUp;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
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
