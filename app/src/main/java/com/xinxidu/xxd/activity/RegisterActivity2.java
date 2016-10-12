package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.base.SysApplication;
import com.xinxidu.xxd.event.UserLoginEvent;
import com.xinxidu.xxd.fragments.MyFragment;
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

/**
 * Created by limingquan on 2016/9/27.
 */
public class RegisterActivity2 extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.back1)
    RelativeLayout back1;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_ok)
    TextView tvTitleOk;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    private SharedPreferences sp;
    private String etNicknameValue;
    private String etPwdValue;
    public static void startRegisterActivity2(Context context) {
        Intent intent = new Intent(context, RegisterActivity2.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.register_activity2);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
        sp = this.getSharedPreferences("times", 8);
    }

    @OnClick({R.id.back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_next:
                regRequest();
                break;
        }
    }

    private void regRequest() {
        etNicknameValue=etNickname.getText().toString();
        etPwdValue=etPwd.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("times",sp.getString("times", ""));
        map.put("name",etNicknameValue);
        map.put("pwd",etPwdValue);
        map.put("source","0");
        OkHttpUtils.get().url(Compares.REG_URL).params(map).build().execute(new StringCallback() {
            @Override
            public void onBefore(okhttp3.Request request) {
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
                    Log.v("reg_sucess", "sucess");
                    Toast.makeText(RegisterActivity2.this, "注册成功", Toast.LENGTH_SHORT).show();
                    commitEvent();
                }else {
                    Toast.makeText(RegisterActivity2.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void commitEvent() {
        UserLoginEvent userLoginEvent = new UserLoginEvent();
        userLoginEvent.setUserName(etNicknameValue);
        userLoginEvent.setUserPass(etPwdValue);
        EventBus.getDefault().post(userLoginEvent);
        SysApplication.getInstance().exit();
       // MyAccountInfoActivity.startMyAccountInfoActivity(this);

    }

}
