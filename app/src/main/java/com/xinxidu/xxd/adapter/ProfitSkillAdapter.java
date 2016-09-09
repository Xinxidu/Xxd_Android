package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.bean.DayInvestmentEvent;
import com.xinxidu.xxd.bean.ProfitSkillEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class ProfitSkillAdapter extends RecyclerView.Adapter<ProfitSkillAdapter.ViewHolder> {
    private final Context mContext;


    private List<ProfitSkillEvent> mItemList = new ArrayList<ProfitSkillEvent>();

    public ProfitSkillAdapter(Context mContext, List<ProfitSkillEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<ProfitSkillEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public ProfitSkillAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.profit_skill_item, parent, false);
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

        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
