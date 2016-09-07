package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountInfoActivity extends AppCompatActivity {
    public static void startAccountInfoActivity(Context context) {
        Intent intent = new Intent(context, AccountInfoActivity.class);
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
    @BindView(R.id.ll_frshop_head)
    LinearLayout llFrshopHead;
    @BindView(R.id.tv111)
    TextView tv111;
    @BindView(R.id.tv222)
    TextView tv222;
    @BindView(R.id.tv333)
    TextView tv333;
    @BindView(R.id.rl_accountdetail)
    RelativeLayout rlAccountdetail;
    @BindView(R.id.tv1122)
    TextView tv1122;
    @BindView(R.id.rl_5555)
    RelativeLayout rl5555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        ButterKnife.bind(this);
        tvTitle.setText("账户信息");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
