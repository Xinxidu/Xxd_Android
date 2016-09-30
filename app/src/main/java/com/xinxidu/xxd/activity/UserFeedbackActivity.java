package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    public static void startUserFeedbackActivity(Context context) {
        Intent intent = new Intent(context, UserFeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        ButterKnife.bind(this);
        tvTitle.setText("用户反馈");
        etInfo.clearFocus();
    }


    @OnClick({R.id.back, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_commit:
//                if (!etInfo.getText().equals("")&&!etPhone.getText().equals("")){
//
//                }else {
//
//                }
                Toast.makeText(UserFeedbackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
