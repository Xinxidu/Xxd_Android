package com.xinxidu.xxd.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.utils.BuyConfirmDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 交易  周期卖
 */
public class CycSellFragment extends Fragment {
    @BindView(R.id.bt_collect)
    Button btCollect;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.bt_jian)
    TextView btJian;
    @BindView(R.id.tv_price_num)
    TextView tvPriceNum;
    @BindView(R.id.bt_jia)
    TextView btJia;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.bt_jian1)
    TextView btJian1;
    @BindView(R.id.tv_price_num1)
    TextView tvPriceNum1;
    @BindView(R.id.bt_jia1)
    TextView btJia1;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_tv_trade_puy_sell)
    TextView tvTvTradePuySell;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_price_sell)
    TextView tvPriceSell;
    @BindView(R.id.tv_num_sell)
    TextView tvNumSell;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    private BuyIntoFragment buyIntoFragment;
    public View view;
    int num = 1000;//数量
    int num1 = 10;//数量
    @BindView(R.id.spinner1)
    Spinner spinner1;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_trade1, container, false);
        ButterKnife.bind(this, view);
        tvTvTradePuySell.setText("周期卖");
        relative.setVisibility(View.VISIBLE);
        tvPriceNum.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        tvPriceNum.setText(String.valueOf(num));
        tvPriceNum1.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        tvPriceNum1.setText(String.valueOf(num1));
        btJian.setTag("+");
        btJia.setTag("-");
        btJian1.setTag("+");
        btJia1.setTag("-");
        spinner();
        return view;
    }

    @OnClick({ R.id.bt_jian, R.id.bt_jia, R.id.bt_jian1, R.id.bt_jia1, R.id.tv_tv_trade_puy_sell})
    public void onClick(View view) {
        switch (view.getId()) {
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
                sellDialog();
                break;
        }
    }

    private void spinner() {
        data_list = new ArrayList<String>();
        data_list.add("白银升贴水1000");
        data_list.add("龙天勇银");
        data_list.add("白银现货排期");
        data_list.add("白银基差1000");
        arr_adapter= new ArrayAdapter<String>(getActivity(), R.layout.spinner1, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.spinner);
        //加载适配器
        spinner1.setAdapter(arr_adapter);
    }

    private void sellDialog() {
        BuyConfirmDialog.Builder builder = new BuyConfirmDialog.Builder(getActivity());
        builder.setTitle("委托调期卖出确认");
        builder.setAccount("88888");
        builder.setTradeName(spinner1.getSelectedItem().toString());
        builder.setNumber(tvPriceNum.getText().toString());
        builder.setPrice(tvPriceNum1.getText().toString());
        builder.setPositiveButton("确定卖出", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "卖出成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    public void jian() {
        if (btJian.getTag().equals("+")) {
            if (--num < 1) { //先减，再判断
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
            if (++num <= 1) {  //先加，再判断
                num--;
                Toast.makeText(getActivity(), "不能小于0", Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum.setText(String.valueOf(num));
            }
        }
    }

    public void jian1() {
        if (btJian1.getTag().equals("+")) {
            if (--num1 < 1) { //先减，再判断
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
            if (++num1 <= 1) {  //先加，再判断
                num1--;
                Toast.makeText(getActivity(), "不能小于0", Toast.LENGTH_SHORT).show();
            } else {
                tvPriceNum1.setText(String.valueOf(num1));
            }
        }
    }
}
