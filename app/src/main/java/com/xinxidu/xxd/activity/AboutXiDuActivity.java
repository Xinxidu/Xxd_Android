package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.utils.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/8/26.
 */
public class AboutXiDuActivity extends Activity {

    public static void startAboutXiDuActivity(Context context) {
        Intent intent = new Intent(context, AboutXiDuActivity.class);
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
    @BindView(R.id.rl_exchange)
    RelativeLayout rlExchange;
    @BindView(R.id.tv_frshop_project_num)
    TextView tvFrshopProjectNum;
    @BindView(R.id.rl_xidu)
    RelativeLayout rlXidu;
    @BindView(R.id.tv_frshop_skiller_num)
    TextView tvFrshopSkillerNum;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.rl_contact)
    RelativeLayout rlContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_xidu);
        ButterKnife.bind(this);
        tvTitle.setText("关于西都石油");
    }

    @OnClick({R.id.back,  R.id.rl_exchange,R.id.rl_xidu, R.id.rl_news, R.id.rl_contact})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_exchange:
                AboutXiDuDetailActivity.startAboutXiDuDetailActivity(this);
                break;
            case R.id.rl_xidu:
                AboutXiDuDetailActivity.startAboutXiDuDetailActivity(this);
                break;
            case R.id.rl_news:
                AboutXiDuDetailActivity.startAboutXiDuDetailActivity(this);
                break;
            case R.id.rl_contact:

                break;
        }
    }
}
