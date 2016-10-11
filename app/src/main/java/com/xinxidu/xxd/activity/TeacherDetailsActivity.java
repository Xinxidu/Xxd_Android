package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/10/11.
 */
public class TeacherDetailsActivity extends Activity {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_touxian)
    TextView tvTouxian;
    @BindView(R.id.tv_tiem)
    TextView tvTiem;
    @BindView(R.id.tv_shanchang)
    TextView tvShanchang;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view1)
    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_details_activity);
        ButterKnife.bind(this);
        tvTitle.setText("名师资料");
        final String image = getIntent().getStringExtra("image");
        Glide.with(this).load(image).into(ivIcon);
        tvName.setText(getIntent().getStringExtra("name"));
        tvTouxian.setText(getIntent().getStringExtra("touxian"));
        tvTiem.setText(getIntent().getStringExtra("time"));
        tvShanchang.setText(getIntent().getStringExtra("shanchang"));
        tvJianjie.setText(getIntent().getStringExtra("jianjie"));
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
