package com.xinxidu.xxd.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.HotTradeAdapter;
import com.xinxidu.xxd.event.HotTradeEvent;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotTradeFragment extends Fragment {

    HotTradeAdapter mHotTradeAdapter;

    private ArrayList<HotTradeEvent> mItem;
    private RecyclerView mRecyclerView;


    public HotTradeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_trade, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<HotTradeEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mHotTradeAdapter = new HotTradeAdapter(getActivity());
        mRecyclerView.setAdapter(mHotTradeAdapter);
        mHotTradeAdapter.setData(mItem);
        mHotTradeAdapter.notifyDataSetChanged();
//        }
        return view;
    }
}
