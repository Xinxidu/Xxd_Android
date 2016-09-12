package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/12.
 */
public class ElectricTradeActivity extends Activity {

    public static void startElectricTradeActivity(Context context) {
        Intent intent = new Intent(context, ElectricTradeActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_ok)
    TextView tvTitleOk;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.tv_yuanyou)
    TextView tvYuanyou;
    @BindView(R.id.tv_ipe)
    TextView tvIpe;
    @BindView(R.id.tv_wh)
    TextView tvWh;
    @BindView(R.id.tv_gjhj)
    TextView tvGjhj;
    @BindView(R.id.tv_qqzs)
    TextView tvQqzs;
    @BindView(R.id.tv_djs)
    TextView tvDjs;
    @BindView(R.id.tv_shhj)
    TextView tvShhj;
    @BindView(R.id.tv_shqh)
    TextView tvShqh;
    @BindView(R.id.tv_jgs)
    TextView tvJgs;
    @BindView(R.id.tv_ggs)
    TextView tvGgs;
    @BindView(R.id.tv_xhdz)
    TextView tvXhdz;
    @BindView(R.id.tv_tks)
    TextView tvTks;
    @BindView(R.id.tv_qls)
    TextView tvQls;
    @BindView(R.id.tv_dyyt)
    TextView tvDyyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electric_trade_activity);
        ButterKnife.bind(this);
        tvTitle.setText("电交所");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
