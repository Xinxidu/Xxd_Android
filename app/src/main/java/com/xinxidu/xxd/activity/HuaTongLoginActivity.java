package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 华通登录
 */
public class HuaTongLoginActivity extends Activity {

    public static void startHuaTongLoginActivity(Context context) {
        Intent intent = new Intent(context, HuaTongLoginActivity.class);
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
    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_pass)
    EditText etLoginPass;
    @BindView(R.id.tv_for_verification_code)
    TextView tvForVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.ll_login_body)
    LinearLayout llLoginBody;
    @BindView(R.id.cb_mima)
    CheckBox cbMima;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.tv_login_commit)
    TextView tvLoginCommit;
    @BindView(R.id.tv_open_account)
    TextView tvOpenAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hua_tong_lagin_activity);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
    }

    @OnClick({R.id.back, R.id.tv_for_verification_code, R.id.tv_login_commit, R.id.tv_open_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_for_verification_code:
                break;
            case R.id.tv_login_commit:
                break;
            case R.id.tv_open_account:
                HuaTongRegisterBasicActivity.startHuaTongRegisterBasicActivity(this);
                break;
        }
    }
}
