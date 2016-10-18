package com.xinxidu.xxd.fragments.marketunscramble;

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
import com.xinxidu.xxd.adapter.TimeNewsAdapter;
import com.xinxidu.xxd.event.TimeNewsEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/10/18.
 */
public class MarketUnscrambleFragment extends Fragment {

    private static final String ARG_PARAM_URL = "param1";
    private static final String ARG_PARAM_CLASS = "param2";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    MarketUnscrambleAdapter mMarketUnscrambleAdapter;

    private ArrayList<TimeNewsEvent> mItem;

    public static MarketUnscrambleFragment newInstance(String url, Class startClass) {
        MarketUnscrambleFragment fragment = new MarketUnscrambleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_URL, url);
        args.putString(ARG_PARAM_CLASS, startClass.getName());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.market_unscramble_fragment, container, false);
            ButterKnife.bind(this, view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mItem = new ArrayList<TimeNewsEvent>();
            mItem.add(null);

            mMarketUnscrambleAdapter = new MarketUnscrambleAdapter(getActivity());
            mRecyclerView.setAdapter(mMarketUnscrambleAdapter);
            mMarketUnscrambleAdapter.setData(mItem);
        mMarketUnscrambleAdapter.notifyDataSetChanged();
        return view;
    }
}
