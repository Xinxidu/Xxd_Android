package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.XiduNewsEvent;
import com.xinxidu.xxd.netWork.XiduNewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class XiduNewsAdapter extends RecyclerView.Adapter<XiduNewsAdapter.ViewHolder> {
    private final Context mContext;
    private List<XiduNewsBean.ResultListBean> mItemList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public XiduNewsAdapter(Context mContext, List<XiduNewsBean.ResultListBean> itemList) {
        mItemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xidu_news_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //
        holder.title.setText(mItemList.get(position).Title);
        holder.keywords.setText("关键词 :"+mItemList.get(position).Keywords);
        holder.senddate.setText(mItemList.get(position).Senddate);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size() == 0 ? 0 : mItemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.keywords)
        TextView keywords;
        @BindView(R.id.senddate)
        TextView senddate;
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
