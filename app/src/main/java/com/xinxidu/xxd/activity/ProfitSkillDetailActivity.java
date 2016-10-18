package com.xinxidu.xxd.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.event.XiduNewsDetails;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ProfitSkillDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.news_webView)
    WebView newsWebView;
    protected static final String URL = "http://app.service.xiduoil.com/Detail";
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_skill_detail);
        ButterKnife.bind(this);
        tvTitle.setText("盈利技巧详情");
        WebSettings settings = newsWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        newsWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        id = getIntent().getStringExtra("id");
        initNet();
    }
    private void initNet() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("type", "OfficialDto");
        OkHttpUtils.get().url(URL)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.v("newdeatilerror", "33333");
                    }

                    @Override
                    public void onResponse(final String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("sucess", response);
                                try {
                                    XiduNewsDetails dataBean = new Gson().fromJson(response, XiduNewsDetails.class);
                                    List<XiduNewsDetails.DataBean> data = dataBean.getData();
                                    for (int i = 0; i < data.size(); i++) {
                                        if (!TextUtils.isEmpty(data.get(i).getBody())) {
                                            newsWebView.loadDataWithBaseURL(null, data.get(i).getBody(), "text/html", "utf-8", null);
                                            Log.v("fail", data.get(i).getBody());
                                        } else {
                                            Log.v("fail", "fail");
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
