package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtionMessageActivity extends AppCompatActivity {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startAtionMessageActivity(Context context) {
        Intent intent = new Intent(context, AtionMessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ation_message);
        ButterKnife.bind(this);
        tvTitle.setText("活动消息");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
