package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.HotTradeEvent;
import com.xinxidu.xxd.netWork.HotTradeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class HotTradeAdapter extends RecyclerView.Adapter<HotTradeAdapter.ViewHolder> {
    private final Context mContext;

    private List<HotTradeBean> mItemList = new ArrayList<HotTradeBean>();
    private OnItemClickListener mOnItemClickListener;
    private boolean isMarket;
    private int itemPosition;

    public HotTradeAdapter(Context mContext, List<HotTradeBean> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<HotTradeBean> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public void setIsMarket(boolean isMarket) {
        this.isMarket = isMarket;
        notifyDataSetChanged();
    }

    public void setItemPosition(int position) {
        this.itemPosition = position;
        notifyDataSetChanged();
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
        holder.tvSilverWater.setText(mItemList.get(position).getName());
        holder.tvNewest.setText(mItemList.get(position).getLatestPrice());
        holder.tvBusiness.setText(mItemList.get(position).getBuy1());
        if (isMarket) {
            holder.tvDownUp.setText(mItemList.get(position).getValueOfUpOrDown());
//            if ((mItemList.get(position).getUpsAndDowns()) > 0) {
//                holder.tvDownUp.setTextColor(Color.BLUE);
//            }
        } else {
            holder.tvDownUp.setText(mItemList.get(position).getUpsAndDowns());
        }

        switch (itemPosition) {
            case 0:
                holder.tvVolume.setText(mItemList.get(position).getHighestPrice());
                break;
            case 1:
                holder.tvVolume.setText(mItemList.get(position).getLowestPrice());
                break;
            case 2:
                holder.tvVolume.setText(mItemList.get(position).getAveragePrice());
                break;
            case 3:
                holder.tvVolume.setText(mItemList.get(position).getBalancePrice());
                break;
            case 4:
                holder.tvVolume.setText(mItemList.get(position).getYesterdayBalance());
                break;
            case 5:
                holder.tvVolume.setText(mItemList.get(position).getYesterdayClose());
                break;
            case 6:
                holder.tvVolume.setText(mItemList.get(position).getVolume());
                break;
            case 7:
                holder.tvVolume.setText(mItemList.get(position).getObv());
                break;
            case 8:
                holder.tvVolume.setText(mItemList.get(position).getSale1());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
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
