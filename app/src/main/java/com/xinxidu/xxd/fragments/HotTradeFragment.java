package com.xinxidu.xxd.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.HotTradeAdapter;
import com.xinxidu.xxd.event.HotTradeEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotTradeFragment extends Fragment {

    HotTradeAdapter mHotTradeAdapter;
    @BindView(R.id.tv_down_up)
    TextView tvDownUp;
    @BindView(R.id.tv_volume)
    TextView tvVolume;

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
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_down_up, R.id.tv_volume})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_down_up:
                showDialog();
                break;
            case R.id.tv_volume:
                showDialog1();
                break;
        }
    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] cities = {"最高", "最低", "开盘", "昨收", "昨结", "现量", "持货量", "成交额", "成交量"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvVolume.setText(" " + cities[which]);
            }
        });
        builder.show();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] cities = {"涨跌幅", "振幅"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvDownUp.setText(" " + cities[which]);
            }
        });
        builder.show();
    }
}
