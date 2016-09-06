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
import com.xinxidu.xxd.adapter.HotActivityAdapter;
import com.xinxidu.xxd.adapter.MarketItemAdapter;
import com.xinxidu.xxd.bean.HotActivityEvent;
import com.xinxidu.xxd.bean.MarketItemEvent;

import java.util.ArrayList;

/**
 * Created by limingquan on 2016/9/6.
 */
public class MarketItemFragment extends Fragment{

    MarketItemAdapter mMarketItemAdapter;

    private ArrayList<MarketItemEvent> mItem;
    private RecyclerView mRecyclerView;
    public MarketItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.market_item_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<MarketItemEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {

        mMarketItemAdapter = new MarketItemAdapter(getActivity());
        mRecyclerView.setAdapter(mMarketItemAdapter);
        mMarketItemAdapter.setData(mItem);
        mMarketItemAdapter.notifyDataSetChanged();
//        }
        return view;
    }
}
