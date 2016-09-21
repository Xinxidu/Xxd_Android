package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.models.JsonData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExchangeInfoActivity extends AppCompatActivity {

    @BindView(R.id.back)
    RelativeLayout back;
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
    protected static final String HOST = "http://175.102.13.51:8080/XDSY/ZhuBan?type=.guanwang&defference=jiaoyi&indexPage=0";
    public static void startExchangeInfoActivity(Context context) {
        Intent intent = new Intent(context, ExchangeInfoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_info);
        ButterKnife.bind(this);
        tvTitle.setText("交易所简介");
        jiaoyiwebRequest();
    }

    private void jiaoyiwebRequest() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(HOST)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.v("fail", e.toString());
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("sucess", res);

                        try {
                            JsonData dataBean = new Gson().fromJson(res,JsonData.class);
                            if (!TextUtils.isEmpty(dataBean.getData().getJiaoyi())){
                                String string = null;
                                try {
                                    string = URLDecoder.decode(dataBean.getData().getJiaoyi(),"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                exchangeWebView.loadDataWithBaseURL(null,dataBean.getData().getJiaoyi(),"text/html","utf-8",null);
                                Log.v("jiaoyiUrl",dataBean.getData().getJiaoyi());
                            }
                            else {
                                Log.v("fail", "false");
                            }
                        } catch (Exception e) {
                            Log.v("exception1", e.toString());
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

