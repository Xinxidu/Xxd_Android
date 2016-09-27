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
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.EntrustItemAdapter;
import com.xinxidu.xxd.event.EntrustItemEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/8.
 */
public class HoldGoodsGragment extends Fragment {
    EntrustItemAdapter mEntrustItemAdapter;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_safety)
    TextView tvSafety;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.tv_fund)
    TextView tvFund;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.tv_now_fund)
    TextView tvNowFund;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.tv_profit)
    TextView tvProfit;
    @BindView(R.id.tv_actual_assets)
    TextView tvActualAssets;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArrayList<EntrustItemEvent> mItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hold_goods_trade_fragment, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<EntrustItemEvent>();
        mItem.add(null);

        mEntrustItemAdapter = new EntrustItemAdapter(getActivity());
        recyclerView.setAdapter(mEntrustItemAdapter);
        mEntrustItemAdapter.setData(mItem);
        mEntrustItemAdapter.notifyDataSetChanged();
    }
}
