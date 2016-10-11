package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.ProfitSkillEvent;
import com.xinxidu.xxd.netWork.ProfitSkillBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class ProfitSkillAdapter extends RecyclerView.Adapter<ProfitSkillAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ProfitSkillBean> mItemList;
    private XiduNewsAdapter.OnItemClickListener mOnItemClickListener;

    public void setData(ArrayList<ProfitSkillBean> List){
        mItemList.addAll(List);
    }

    public ProfitSkillAdapter(Context mContext, List<ProfitSkillBean> itemList) {
        this.mItemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.profit_skill_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvTitle.setText(mItemList.get(position).getTitle());
        holder.tvKeywords.setText(mItemList.get(position).getKeywords());
        holder.tvSenddate.setText(mItemList.get(position).getSenddate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size() == 0 ? 0 : mItemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_keywords)
        TextView tvKeywords;
        @BindView(R.id.tv_senddate)
        TextView tvSenddate;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemClickListener(XiduNewsAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
