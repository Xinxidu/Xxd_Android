package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/29.
 */
public class TeacherTeamActivity1 extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startTeacherTeamActivity1(Context context) {
        Intent intent = new Intent(context, TeacherTeamActivity1.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_team_activity1);
        ButterKnife.bind(this);
        tvTitle.setText("名师团队");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
