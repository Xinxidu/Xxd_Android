package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.DayInvestmentDetails;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DYX on 2016/9/20 0020.
 */
public class DayInvestmentDetailActivity extends Activity {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.back1)
    RelativeLayout back1;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_ok)
    TextView tvTitleOk;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.exchange_webView)
    WebView exchangeWebView;
    protected static final String HOST = "http://app.service.xiduoil.com/Detail";
    private String id;

    public static void startAboutXiDuDetailActivity(Context context) {
        Intent intent = new Intent(context, AboutXiDuDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_details);
        ButterKnife.bind(this);
        WebSettings settings = exchangeWebView.getSettings();
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        tvTitle.setText("投资策略详情");

        id = getIntent().getStringExtra("id");
        webrequest();
    }

    private void webrequest() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("type", "OfficialDto");

        OkHttpUtils.get()
                .url(HOST)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e) {
                        Log.v("ffffff", "5555555");
                    }

                    @Override
                    public void onResponse(final String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("sucess", response);

                                try {
                                    DayInvestmentDetails dataBean = new Gson().fromJson(response, DayInvestmentDetails.class);
                                    List<DayInvestmentDetails.DataBean> data=dataBean.getData();
                                    for (int i=0;i<data.size();i++){
                                        if (!TextUtils.isEmpty(data.get(i).getBody())) {
                                            exchangeWebView.loadDataWithBaseURL(null, data.get(i).getBody(), "text/html", "utf-8", null);
                                            Log.v("fail11", data.get(i).getBody());
                                        } else {
                                            Log.v("fail", "false");
                                        }
                                    }

                                } catch (Exception e) {
                                    Log.v("exception", e.toString());
                                }
                            }

                        });

                    }
                });
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}