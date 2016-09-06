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
import com.xinxidu.xxd.adapter.HotActivityAdapter;
import com.xinxidu.xxd.adapter.HotTradeAdapter;
import com.xinxidu.xxd.bean.HotActivityEvent;
import com.xinxidu.xxd.bean.HotTradeEvent;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotFragment extends Fragment {
    HotActivityAdapter mHotActivityAdapter;

    private ArrayList<HotActivityEvent> mItem;
    private RecyclerView mRecyclerView;
    public HotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<HotActivityEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mHotActivityAdapter = new HotActivityAdapter(getActivity());
        mRecyclerView.setAdapter(mHotActivityAdapter);
        mHotActivityAdapter.setData(mItem);
        mHotActivityAdapter.notifyDataSetChanged();
//        }
        return view;
    }
}
