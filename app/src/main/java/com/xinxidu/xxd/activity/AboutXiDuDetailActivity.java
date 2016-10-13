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
import com.xinxidu.xxd.models.XiduInfoData;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutXiDuDetailActivity extends Fragment {
    @BindView(R.id.exchange_webView)
    WebView exchangeWebView;
    protected static final String URL = "http://app.service.xiduoil.com/ZhuBan?type=.guanwang&defference=gongsi";
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exchange_info, container, false);
        ButterKnife.bind(this, view);
        tv_title.setText("鑫西都");
        WebSettings settings = exchangeWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webRequest();
        return view;
    }

    private void webRequest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(URL)
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
                        Log.v("sucess1", res);

                        try {
                            XiduInfoData dataBean = new Gson().fromJson(res, XiduInfoData.class);
                            if (!TextUtils.isEmpty(dataBean.getData().getGongsi())) {
//                                String string = null;
//                                try {
//                                    string = URLDecoder.decode(dataBean.getData().getGongsi(), "utf-8");
//                                } catch (UnsupportedEncodingException e) {
//                                    e.printStackTrace();
//                                }
                                exchangeWebView.loadDataWithBaseURL(null, dataBean.getData().getGongsi(), "text/html", "utf-8", null);
                                Log.v("gongsiUrl", dataBean.getData().getGongsi());
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