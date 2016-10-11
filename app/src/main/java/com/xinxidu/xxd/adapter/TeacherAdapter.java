package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.netWork.TeacherBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {
    private final Context mContext;

    private List<TeacherBean> mItemList = new ArrayList<TeacherBean>();

    private OnItemClickListener mOnItemClickListener;

    public TeacherAdapter(Context mContext, List<TeacherBean> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<TeacherBean> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public TeacherAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.teacher_adapter_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvSynopsis.setText(mItemList.get(position).getTouxian());
        holder.tvExplain.setText(mItemList.get(position).getShanchang());
        Glide.with(mContext).load(mItemList.get(position).getImage()).into(holder.ivIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_synopsis)
        TextView tvSynopsis;
        @BindView(R.id.tv_explain)
        TextView tvExplain;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int postion);
    }
}
