package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 财经一点通 登录
 */
public class LoginActivity extends Activity {

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
    @BindView(R.id.ll_login_body)
    RelativeLayout llLoginBody;
    @BindView(R.id.cb_mima)
    CheckBox cbMima;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.tv_login_commit)
    TextView tvLoginCommit;
    @BindView(R.id.tv_back_pass)
    TextView tvBackPass;
    @BindView(R.id.tv_open_account)
    TextView tvOpenAccount;
    private SharedPreferences sp;
    private String etLoginUserValue;
    private String etLoginPassValue;

    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            cbMima.setChecked(true);
            etLoginUser.setText(sp.getString("USER_NAME", ""));
            etLoginPass.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                //设置默认是自动登录状态
                cbAuto.setChecked(true);
                //跳转界面
//                Intent intent = new Intent(LoginActivity.this, HotActivity.class);
//                startActivity(intent);

            }
        }
    }

    @OnClick({R.id.back, R.id.tv_login_commit, R.id.tv_open_account, R.id.cb_mima, R.id.cb_auto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_login_commit:
                login();
                break;
            case R.id.tv_open_account:
                RegisterBasicActivity.startRegisterBasicActivity(this);
                break;
            case R.id.cb_mima:
                if (cbMima.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
            case R.id.cb_auto:
                if (cbAuto.isChecked()) {
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
                break;
        }
    }

    private void login() {
        etLoginUserValue = etLoginUser.getText().toString();
        etLoginPassValue = etLoginPass.getText().toString();

        if (etLoginUserValue.equals("xxd") && etLoginPassValue.equals("123")) {

            //登录成功和记住密码框为选中状态才保存用户信息
            if (cbMima.isChecked()) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", etLoginUserValue);
                editor.putString("PASSWORD", etLoginPassValue);
                editor.commit();
            }

            //跳转界面
//            Intent intent = new Intent(LoginActivity.this, HotActivity.class);
//            startActivity(intent);
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
        }
    }
}
