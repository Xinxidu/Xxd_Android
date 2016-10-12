package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by limingquan on 2016/9/8.
 */
public class RegisterBasicActivity extends Activity {
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
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_ese)
    EditText etEse;
    private TimeCount time;
    private boolean validInput;
    private String etPhoneValue;
    private String etEseValue;
    private SharedPreferences sp;
    public static void startRegisterBasicActivity(Context context) {
        Intent intent = new Intent(context, RegisterBasicActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.register_basic_activity);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
        sp = this.getSharedPreferences("times",8);
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.back, R.id.tv_send, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_send:
                String phoneNums = etPhone.getText().toString();
                if (judgePhoneNums(phoneNums)) {
                    //点击发送验证码倒计时
                    time.start();
                }
                getCodeRequest();
                break;
            case R.id.tv_next:
                validCodeRequst();
//                if (isValidInput()) {
//                    RegisterActivity2.startRegisterActivity2(this);
//                }
                break;
        }
    }

    private void getCodeRequest(){
        etPhoneValue = etPhone.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("type","appSMS");
        map.put("mob",etPhoneValue);
        map.put("source","0");
        OkHttpUtils.get().url(Compares.GETCODE_URL).params(map).build().execute(new StringCallback() {
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
                if (object.getInt("k") == 1) {
                    Log.v("getcode_sucess", "sucess");
                    }else {
                    Log.v("getcode_fail","fail");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void validCodeRequst(){
        etPhoneValue = etPhone.getText().toString();
        etEseValue = etEse.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("type","appSMS");
        map.put("mob",etPhoneValue);
        map.put("code",etEseValue);
        OkHttpUtils.get().url(Compares.VALIDCODE_URL).params(map).build().execute(new StringCallback() {
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
                parseData2(response);
            }
        });
    }
    private void parseData2(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getInt("key") == 1) {
                    String times = object.getString("times");
                    sp.edit().putString("times",times).commit();
                    RegisterActivity2.startRegisterActivity2(this);

                }else {
                    Toast.makeText(this, "验证失败!", Toast.LENGTH_SHORT).show();
                    Log.v("validCode_fail","fail");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "请正确输入手机号码！", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    //为空判断
    public boolean isValidInput() {
        String userName = etPhone.getText().toString();
        String ese = etEse.getText().toString();
        if (userName.equals("")) {
            Toast.makeText(getApplicationContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (ese.equals("")) {
            Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tvSend.setText("重新获取");
            tvSend.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tvSend.setClickable(false);
            tvSend.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
