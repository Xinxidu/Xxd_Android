package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.xinxidu.xxd.models.XiduInfoData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutXiDuDetailActivity extends Fragment {
    @BindView(R.id.xidu_webView)
    WebView xiduWebView;
    protected static final String URL = "http://app.service.xiduoil.com/ZhuBan?type=.guanwang&defference=gongsi";
    public static void startAboutXiDuDetailActivity(Context context) {
        Intent intent = new Intent(context, AboutXiDuDetailActivity.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_about_xi_du_detail, container, false);
        ButterKnife.bind(this, view);
        webRequest();
        return view;
    }

    private void webRequest(){
        //创建okHttpClient对象
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
                        Log.v("sucess2", res);

                        try {
                            XiduInfoData dataBeans = new Gson().fromJson(res,XiduInfoData.class);
                            if (!TextUtils.isEmpty(dataBeans.getData().getGongsi())){
                                String string = null;
                                try {
                                    string = URLDecoder.decode(dataBeans.getData().getGongsi(),"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                xiduWebView.loadDataWithBaseURL(null, dataBeans.getData().getGongsi(),"text/html","utf-8",null);
                               // Log.v("url",DataBeans.getData().getGongsi());
                            }
                            else {
                                Log.v("fail", "false");
                            }
                        } catch (Exception e) {
                            Log.v("exception2", e.toString());
                        }

                   }
                });

            }
        });
    }
}
