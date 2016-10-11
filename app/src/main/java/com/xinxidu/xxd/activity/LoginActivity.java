package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.event.UserLoginEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

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
    RelativeLayout tvOpenAccount;
    private SharedPreferences sp;
    private String etLoginUserValue;
    private String etLoginPassValue;

    // private String id;
//    public static final String URL = "http://localhost:8090/app/controller/avtive/detail/json";


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

                //intnNet();
            }

//    private void intnNet() {
//        Map<String, String> map = new HashMap<>();
//        map.put("id", id);
//    }
        }
    }

    @OnClick({R.id.back, R.id.tv_login_commit, R.id.tv_open_account, R.id.cb_mima, R.id.cb_auto, R.id.tv_back_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_login_commit:
                loginRequest();
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
            case R.id.tv_back_pass:
                BackPassActivity.startBackPassActivity(this);
                break;
        }
    }

    private void loginRequest() {
        etLoginUserValue = etLoginUser.getText().toString();
        etLoginPassValue = etLoginPass.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("name", etLoginUserValue);
        map.put("pwd", etLoginPassValue);
        OkHttpUtils.get().url(Compares.LOGIN_URL).params(map).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request) {
                System.out.println("request=" + request.toString());
                super.onBefore(request);

            }

            @Override
            public void onError(Call call, Exception e) {
                System.out.println("e=" + e.toString());
            }

            @Override
            public void onResponse(String response) {
                parseData(response);
            }
        });
    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getInt("key") == 1) {
                    //System.out.println("请求成功");
                    Log.v("login_sucess", "sucess");
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
                    commitEvent();
                } else {
                    //Log.v("fail3","login-fail");
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void commitEvent() {
        UserLoginEvent userLoginEvent = new UserLoginEvent();
        userLoginEvent.setUserName(etLoginUser.getText().toString());
        userLoginEvent.setUserPass(etLoginPass.getText().toString());
        EventBus.getDefault().post(userLoginEvent);
        LoginActivity.this.finish();
    }
}
