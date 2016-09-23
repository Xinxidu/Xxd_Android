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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.HotTradeAdapter;
import com.xinxidu.xxd.event.HotTradeEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotTradeFragment extends Fragment {

    HotTradeAdapter mHotTradeAdapter;
    @BindView(R.id.tv_down_up)
    Spinner tvDownUp;
    @BindView(R.id.tv_volume)
    Spinner tvVolume;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

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

        mHotTradeAdapter = new HotTradeAdapter(getActivity());
        mRecyclerView.setAdapter(mHotTradeAdapter);
        mHotTradeAdapter.setData(mItem);
        mHotTradeAdapter.notifyDataSetChanged();
        ButterKnife.bind(this, view);
        spinner1();
        spinner2();
        return view;
    }

    private void spinner1() {
        final String[] data_list = {"涨跌幅", "振幅"};
        arr_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner1_trade, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_trade);
        //加载适配器
        tvDownUp.setAdapter(arr_adapter);
    }

    private void spinner2() {
        final String[] data_list = {"最高", "最低", "开盘", "昨收", "昨结", "现量", "持货量", "成交额", "成交量"};
        arr_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner1_trade, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner_trade);
        //加载适配器
        tvVolume.setAdapter(arr_adapter);
    }
}
