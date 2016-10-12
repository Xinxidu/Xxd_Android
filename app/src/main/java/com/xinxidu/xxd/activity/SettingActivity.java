package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.base.SysApplication;
import com.xinxidu.xxd.utils.SystemUpdateDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.my_info)
    TextView myInfo;
    @BindView(R.id.user_feedback)
    TextView userFeedback;
    @BindView(R.id.version_updating)
    TextView versionUpdating;

    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitle.setText("设置");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.my_info, R.id.user_feedback, R.id.version_updating})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_info:
                MyAccountInfoActivity.startMyAccountInfoActivity(this);
                break;
            case R.id.user_feedback:
                UserFeedbackActivity.startUserFeedbackActivity(this);
                break;
            case R.id.version_updating:
                showRemindDialog();
                break;
        }
    }

    private void showRemindDialog() {
        SystemUpdateDialog.Builder builder = new SystemUpdateDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("软件已升级为最高版本6.0\n是否立即升级");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("否",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
