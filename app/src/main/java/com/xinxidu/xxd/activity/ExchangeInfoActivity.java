package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
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

public class ExchangeInfoActivity extends Fragment {

    @BindView(R.id.exchange_webView)
    WebView exchangeWebView;
    protected static final String HOST = "http://app.service.xiduoil.com/ZhuBan?type=.guanwang&defference=jiaoyi";

    public static void startExchangeInfoActivity(Context context) {
        Intent intent = new Intent(context, ExchangeInfoActivity.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exchange_info, container, false);
        ButterKnife.bind(this, view);
        WebSettings settings = exchangeWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        jiaoyiwebRequest();
        return view;
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("sucess", res);

                        try {
                            JsonData dataBean = new Gson().fromJson(res, JsonData.class);
                            if (!TextUtils.isEmpty(dataBean.getData().getJiaoyi())) {
                                exchangeWebView.loadDataWithBaseURL(null, dataBean.getData().getJiaoyi(), "text/html", "utf-8", null);
                                Log.v("jiaoyiUrl", dataBean.getData().getJiaoyi());
                            } else {
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
}

