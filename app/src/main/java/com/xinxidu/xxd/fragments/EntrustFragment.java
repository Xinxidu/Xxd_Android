package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.EntrustItemAdapter;
import com.xinxidu.xxd.adapter.MarketItemAdapter;
import com.xinxidu.xxd.event.EntrustItemEvent;
import com.xinxidu.xxd.event.MarketItemEvent;

import java.util.ArrayList;

/**
 * Created by limingquan on 2016/9/8.
 * 委托
 */
public class EntrustFragment extends Fragment {
    EntrustItemAdapter mEntrustItemAdapter;

    private ArrayList<EntrustItemEvent> mItem;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.entrust_trade_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<EntrustItemEvent>();
        mItem.add(null);

        mEntrustItemAdapter = new EntrustItemAdapter(getActivity());
        mRecyclerView.setAdapter(mEntrustItemAdapter);
        mEntrustItemAdapter.setData(mItem);
        mEntrustItemAdapter.notifyDataSetChanged();
        return view;
    }
}
