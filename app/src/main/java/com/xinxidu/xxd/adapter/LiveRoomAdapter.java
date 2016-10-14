package com.xinxidu.xxd.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.LiveTelecastURLActivity;
import com.xinxidu.xxd.event.LiveRoomEvent;
import com.xinxidu.xxd.utils.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/5.
 */
public class LiveRoomAdapter extends RecyclerView.Adapter<LiveRoomAdapter.ViewHolder> {
    private final Context mContext;
    private List<LiveRoomEvent> mItemList = new ArrayList<LiveRoomEvent>();

    public LiveRoomAdapter(Context mContext, List<LiveRoomEvent> itemList, Context context) {
        mItemList = itemList;
        this.mContext = context;
    }

    public void setData(ArrayList<LiveRoomEvent> timeList) {
        //mItemList.clear();
        mItemList.addAll(timeList);
    }

    public LiveRoomAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.live_room_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveTelecastURLActivity.startLiveTelecastURLActivityy(mContext);
//                String url = "http://win.xxidu.com/";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        SelectableRoundedImageView imageView;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.relative)
        RelativeLayout relative;
        @BindView(R.id.bt_live_telecast)
        Button btLiveTelecast;
        @BindView(R.id.tv_tedian)
        TextView tvTedian;
        @BindView(R.id.tv_tuijian)
        TextView tvTuijian;
        View itemView;

        ViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }
}
