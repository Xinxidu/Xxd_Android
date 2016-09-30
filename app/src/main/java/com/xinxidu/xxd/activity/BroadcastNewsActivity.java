package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

public class BroadcastNewsActivity extends AppCompatActivity {

    public static void startBroadcastNewsActivity(Context context) {
        Intent intent = new Intent(context, BroadcastNewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_news);
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_title_ok = (TextView) findViewById(R.id.tv_title_ok);
        tv_title_ok.setText("收藏");
        tv_title.setText("直播新闻");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionActivity.startCollectionActivity(BroadcastNewsActivity.this);
            }
        });
    }
}
