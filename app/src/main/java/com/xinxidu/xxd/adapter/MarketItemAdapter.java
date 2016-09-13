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
import com.xinxidu.xxd.event.MarketItemEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class MarketItemAdapter extends RecyclerView.Adapter<MarketItemAdapter.ViewHolder> {
    private final Context mContext;

    private List<MarketItemEvent> mItemList = new ArrayList<MarketItemEvent>();

    public MarketItemAdapter(Context mContext, List<MarketItemEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<MarketItemEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public MarketItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hmarket_adapter_item, parent, false);
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
        return 10;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tv_variety)
        TextView tvVariety;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
        @BindView(R.id.tv_high)
        TextView tvHigh;
        @BindView(R.id.tv_down)
        TextView tvDown;
        @BindView(R.id.line)
        LinearLayout line;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
