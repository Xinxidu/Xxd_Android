package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

public class AboutXXiDuActivity extends AppCompatActivity {

    public static void startAboutXXiDuActivity(Context context) {
        Intent intent = new Intent(context, AboutXXiDuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_xxi_du);
        RelativeLayout back= (RelativeLayout) findViewById(R.id.back);
        TextView tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("关于我们");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
