package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShiPanAccountActivity extends AppCompatActivity {

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
    @BindView(R.id.rl_accountdetail)
    TextView rlAccountdetail;
    @BindView(R.id.rl_todaydinglidan)
    TextView rlTodaydinglidan;
    @BindView(R.id.rl_todaytiaoqidan)
    TextView rlTodaytiaoqidan;
    @BindView(R.id.rl_historydinglidan)
    TextView rlHistorydinglidan;
    @BindView(R.id.rl_historytiaoqidan)
    TextView rlHistorytiaoqidan;
    @BindView(R.id.rl_jiaoyi)
    TextView rlJiaoyi;
    @BindView(R.id.bt_sign_out)
    Button btSignOut;

    public static void startMyShiPanAccountActivity(Context context) {
        Intent intent = new Intent(context, MyShiPanAccountActivity.class);
        context.startActivity(intent);
    }


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

    @OnClick({R.id.rl_accountdetail, R.id.rl_todaydinglidan, R.id.rl_todaytiaoqidan, R.id.rl_historydinglidan, R.id.rl_historytiaoqidan, R.id.rl_jiaoyi, R.id.bt_sign_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_accountdetail:
                AccountInfoActivity.startAccountInfoActivity(this);
                break;
            case R.id.rl_todaydinglidan:
                TodayDecideListActivity.startTodayDecideListActivity(this);
                break;
            case R.id.rl_todaytiaoqidan:
                TodayAdjustListActivity.startTodayAdjustListActivity(this);
                break;
            case R.id.rl_historydinglidan:
                OrderHistoryActivity.startOrderHistoryActivity(this);
                break;
            case R.id.rl_historytiaoqidan:
                OrderHistoryAdjustActivity.startOrderHistoryAdjustActivity(this);
                break;
            case R.id.rl_jiaoyi:
                break;
            case R.id.bt_sign_out:
                break;
        }
    }
}
