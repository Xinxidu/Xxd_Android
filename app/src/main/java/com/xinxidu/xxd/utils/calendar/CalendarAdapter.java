package com.xinxidu.xxd.utils.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import java.util.List;

/**
 * date: 2016/10/19
 * time: 10:20
 * description:
 */

public class CalendarAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CalenderBean> beanList;

    public CalendarAdapter(Context context, List<CalenderBean> list) {
        this.mContext = context;
        this.beanList = list;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_calender, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
//        MyViewHolder viewHolder = new MyViewHolder(View.inflate(mContext,R.layout.item_calender,null));
        return viewHolder;
    }

    public List<CalenderBean> getData() {
        return beanList;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        CalenderBean calenderBean = beanList.get(position);

        if (calenderBean != null) {
            ((MyViewHolder) holder).txtCalenderNum.setText(calenderBean.getNunber());
            ((MyViewHolder) holder).txtCalenderWeek.setText(calenderBean.getWeek());
        }
        if (calenderBean.getCurrentItem()) {//选中#0e2946字体颜色#ffffffff  未选中是字体颜色#333333 背景颜色#ffe5e5e5
            ((MyViewHolder) holder).itemView.setBackgroundColor(Color.parseColor("#0e2946"));
            ((MyViewHolder) holder).txtCalenderNum.setTextColor(Color.parseColor("#ffffffff"));
            ((MyViewHolder) holder).txtCalenderWeek.setTextColor(Color.parseColor("#ffffffff"));
        } else {
            ((MyViewHolder) holder).itemView.setBackgroundColor(Color.parseColor("#ffe5e5e5"));
            ((MyViewHolder) holder).txtCalenderNum.setTextColor(Color.parseColor("#333333"));
            ((MyViewHolder) holder).txtCalenderWeek.setTextColor(Color.parseColor("#333333"));
        }

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (beanList != null) {
            return beanList.size();
        }
        return 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCalenderNum;
        private TextView txtCalenderWeek;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtCalenderNum = (TextView) itemView.findViewById(R.id.calenderNumber);
            txtCalenderWeek = (TextView) itemView.findViewById(R.id.calenderWeek);
        }
    }
}
