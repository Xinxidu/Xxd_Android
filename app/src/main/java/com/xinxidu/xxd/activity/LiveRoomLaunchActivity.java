package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveRoomLaunchActivity extends AppCompatActivity {

    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;

    public static void startLiveRoomLaunchActivity(Context context) {
        Intent intent = new Intent(context, LiveRoomLaunchActivity.class);
        context.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room_launch);
        ButterKnife.bind(this);
       // baseTitleLayout.setBackgroundColor(Color.TRANSPARENT);

    }

    @OnClick({R.id.back, R.id.bt_live})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_live:
                RegisterBasicActivity.startRegisterBasicActivity(this);
                finish();
                break;
        }
    }
}
