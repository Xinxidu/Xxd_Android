package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShiPanAccountActivity extends AppCompatActivity {

    public static void startMyShiPanAccountActivity(Context context) {
        Intent intent = new Intent(context, MyShiPanAccountActivity.class);
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
    @BindView(R.id.rl_accountdetail)
    RelativeLayout rlAccountdetail;
    @BindView(R.id.tv_frshop_project_num)
    TextView tvFrshopProjectNum;
    @BindView(R.id.rl_todaydinglidan)
    RelativeLayout rlTodaydinglidan;
    @BindView(R.id.tv_frshop_skiller_num)
    TextView tvFrshopSkillerNum;
    @BindView(R.id.rl_todaytiaoqidan)
    RelativeLayout rlTodaytiaoqidan;
    @BindView(R.id.rl_historydinglidan)
    RelativeLayout rlHistorydinglidan;
    @BindView(R.id.rl_historytiaoqidan)
    RelativeLayout rlHistorytiaoqidan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shi_pan_account);
        ButterKnife.bind(this);
        tvTitle.setText("我的实盘账户");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.rl_accountdetail, R.id.rl_todaydinglidan, R.id.rl_todaytiaoqidan, R.id.rl_historydinglidan, R.id.rl_historytiaoqidan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_accountdetail:
                AccountInfoActivity.startAccountInfoActivity(this);
                break;
            case R.id.rl_todaydinglidan:
                break;
            case R.id.rl_todaytiaoqidan:
                break;
            case R.id.rl_historydinglidan:
                break;
            case R.id.rl_historytiaoqidan:
                break;
        }
    }
}
