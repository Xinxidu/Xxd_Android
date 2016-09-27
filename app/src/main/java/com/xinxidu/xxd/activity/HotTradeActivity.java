package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

public class HotTradeActivity extends AppCompatActivity {

    public static void startHotTradeActivity(Context context) {
        Intent intent = new Intent(context, HotTradeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_trade);
        RelativeLayout back= (RelativeLayout) findViewById(R.id.back);
        TextView tv_title= (TextView) findViewById(R.id.tv_title);
        TextView tv_title_ok = (TextView) findViewById(R.id.tv_title_ok);
        tv_title_ok.setBackgroundResource(R.drawable.refresh);
        tv_title.setText("热门交易");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
