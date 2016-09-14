package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.GalleryAdapter;
import com.xinxidu.xxd.utils.MyRecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherTeamActivity extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startTeacherTeamActivity(Context context) {
        Intent intent = new Intent(context, TeacherTeamActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.id_recyclerview_horizontal)
    MyRecyclerView mRecyclerView;
    @BindView(R.id.iv_teacher_team)
    ImageView ivTeacherTeam;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private List<Integer> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_team);
        ButterKnife.bind(this);

        tvTitle.setText("名师团队");
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.hanyun,
                R.drawable.lu, R.drawable.li, R.drawable.zuda, R.drawable.luchengxian,
                R.drawable.zhang, R.drawable.liuxiaopeng));
        mList = new ArrayList<Integer>(Arrays.asList(R.drawable.hanyun2,
                R.drawable.lu2, R.drawable.li2, R.drawable.zuda2, R.drawable.luchengxian2,
                R.drawable.zhang2, R.drawable.liuxiaopeng2));

        mRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new GalleryAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                ivTeacherTeam.setImageResource(mList.get(position));
            }
        });

        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ivTeacherTeam.setImageResource(mList.get(position));
            }
        });

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
