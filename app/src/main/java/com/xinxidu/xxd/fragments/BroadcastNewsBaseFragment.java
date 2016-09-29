package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.BroadcastNewsBaseAdapter;
import com.xinxidu.xxd.adapter.BroadcastNewsItemAdapter;
import com.xinxidu.xxd.event.BroadcastNewsItemEvent;

import java.util.ArrayList;

/**
 * Created by limingquan on 2016/9/6.
 */
public class BroadcastNewsBaseFragment extends Fragment{

    BroadcastNewsBaseAdapter mBroadcastNewsBaseAdapter;

    private ArrayList<BroadcastNewsItemEvent> mItem;
    private RecyclerView mRecyclerView;
    public BroadcastNewsBaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.broadcast_news_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<BroadcastNewsItemEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mBroadcastNewsBaseAdapter = new BroadcastNewsBaseAdapter(getActivity());
        mRecyclerView.setAdapter(mBroadcastNewsBaseAdapter);
        mBroadcastNewsBaseAdapter.setData(mItem);
        mBroadcastNewsBaseAdapter.notifyDataSetChanged();
//        }
        return view;
    }
}
