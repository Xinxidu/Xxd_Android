package com.xinxidu.xxd.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 交易  周期卖
 */
public class CycBuyFragment extends Fragment {
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
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_price_sell)
    TextView tvPriceSell;
    @BindView(R.id.tv_num_sell)
    TextView tvNumSell;
    @BindView(R.id.tv_tv_trade_puy_sell)
    TextView tvTvTradePuySell;
    @BindView(R.id.bt_jian)
    TextView btJian;
    @BindView(R.id.tv_price_num)
    TextView tvPriceNum;
    @BindView(R.id.bt_jia)
    TextView btJia;
    @BindView(R.id.bt_jian1)
    TextView btJian1;
    @BindView(R.id.tv_price_num1)
    TextView tvPriceNum1;
    @BindView(R.id.bt_jia1)
    TextView btJia1;
    public View view;
    int num = 1000;//数量
    int num1 = 10;//数量

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_trade, container, false);
        ButterKnife.bind(this, view);
        relative1.setVisibility(View.GONE);
        tvTvTradePuySell.setText("周期买");
        tvPriceNum.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        tvPriceNum.setText(String.valueOf(num));
        tvPriceNum1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        tvPriceNum1.setText(String.valueOf(num1));
        btJian.setTag("+");
        btJia.setTag("-");
        btJian1.setTag("+");
        btJia1.setTag("-");
        return view;
    }

    @OnClick({R.id.bt_name, R.id.bt_jian, R.id.bt_jia, R.id.bt_jian1, R.id.bt_jia1, R.id.tv_tv_trade_puy_sell})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_name:
                showDialog();
                break;
            case R.id.bt_jian:
                jian();
                break;
            case R.id.bt_jia:
                jia();
                break;
            case R.id.bt_jian1:
                jian1();
                break;
            case R.id.bt_jia1:
                jia1();
                break;
            case R.id.tv_tv_trade_puy_sell:
//                buyDialog();
                break;
        }
    }

//    private void buyDialog() {
//
//    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择类型：");
        final String[] cities = {"白银升贴水1000", "龙天勇银", "白银现货排期", "白银基差1000"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btName.setText(" " + cities[which]);
            }
        });
        builder.show();
    }

    public void jian() {
        if (btJian.getTag().equals("+")) {
            if (--num < 0) { //先减，再判断
                num++;
                Toast.makeText(getActivity(), "不能小于0",
                        Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum.setText(String.valueOf(num));

            }
        }
    }

    public void jia() {
        if (btJia.getTag().equals("-")) {
            if (++num < 0) {  //先加，再判断
                num--;
                Toast.makeText(getActivity(), "不能小于0", Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum.setText(String.valueOf(num));
            }
        }
    }

    public void jian1() {
        if (btJian1.getTag().equals("+")) {
            if (--num1 < 0) { //先减，再判断
                num1++;
                Toast.makeText(getActivity(), "不能小于0",
                        Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum1.setText(String.valueOf(num1));

            }
        }
    }

    public void jia1() {
        if (btJia1.getTag().equals("-")) {
            if (++num1 < 0) {  //先加，再判断
                num1--;
                Toast.makeText(getActivity(), "不能小于0", Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum1.setText(String.valueOf(num1));
            }
        }
    }
}
