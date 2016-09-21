package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 */
public class RegisterBasicActivity extends Activity {


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
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.tv_next)
    TextView tvNext;
    private TimeCount time;

    public static void startRegisterBasicActivity(Context context) {
        Intent intent = new Intent(context, RegisterBasicActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_basic_activity);
        ButterKnife.bind(this);
        tvTitle.setText("基本信息");
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.back, R.id.tv_send, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_send:
                //点击发送验证码倒计时
                time.start();
                break;
            case R.id.tv_next:
                break;
        }
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
