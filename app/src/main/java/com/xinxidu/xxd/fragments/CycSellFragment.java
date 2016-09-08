package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 交易周期卖
 */
public class CycSellFragment extends Fragment {
    @BindView(R.id.bt_name)
    Button btName;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.bt_collect)
    Button btCollect;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.bt_jian)
    TextView btJian;
    @BindView(R.id.tv_price_num)
    Button tvPriceNum;
    @BindView(R.id.bt_jia)
    TextView btJia;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_price_sell)
    TextView tvPriceSell;
    @BindView(R.id.tv_num_sell)
    TextView tvNumSell;
    @BindView(R.id.bt_jian1)
    TextView btJian1;
    @BindView(R.id.tv_price_num1)
    Button tvPriceNum1;
    @BindView(R.id.bt_jia1)
    TextView btJia1;
    @BindView(R.id.tv_tv_trade_puy_sell)
    TextView tvTvTradePuySell;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_trade, container, false);
        ButterKnife.bind(this, view);
        relative1.setVisibility(View.GONE);
        tvTvTradePuySell.setText("周期卖");
        return view;
    }

    @OnClick({R.id.bt_name, R.id.relative, R.id.bt_jian, R.id.bt_jia, R.id.relative1, R.id.bt_jian1, R.id.bt_jia1, R.id.tv_tv_trade_puy_sell})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_name:
                break;
            case R.id.relative:
                break;
            case R.id.bt_jian:
                break;
            case R.id.bt_jia:
                break;
            case R.id.relative1:
                break;
            case R.id.bt_jian1:
                break;
            case R.id.bt_jia1:
                break;
            case R.id.tv_tv_trade_puy_sell:
                break;
        }
    }
}
