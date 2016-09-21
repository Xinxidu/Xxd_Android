package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.xinxidu.xxd.event.DayInvestmentDetails;
import com.xinxidu.xxd.models.JsonData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DYX on 2016/9/20 0020.
 */
public class DayInvestmentDetailActivity extends Activity {
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
    @BindView(R.id.xidu_webView)
    WebView xiduWebView;
    private String URL;
    protected static final String HOST = "http://175.102.13.51:8080/XDSY/Detail?id=11870&type=OfficialDto";

    public static void startAboutXiDuDetailActivity(Context context) {
        Intent intent = new Intent(context, AboutXiDuDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_xi_du_detail);
        ButterKnife.bind(this);
        tvTitle.setText("投资策略详情");
        webrequest();
    }

    private void webrequest() {
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
                Log.v("ffffff", "5555555");
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("sucess", res);

                        try {
                            DayInvestmentDetails dataBean = new Gson().fromJson(res,DayInvestmentDetails.class);
                            if (!TextUtils.isEmpty(dataBean.getData().getBody())){
                                String string = null;
                                try {
                                    string = URLDecoder.decode(dataBean.getData().getBody(),"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                xiduWebView.loadDataWithBaseURL(null,dataBean.getData().getBody(),"text/html","utf-8",null);
                            }
                            else {
                                Log.v("fail", "false");
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