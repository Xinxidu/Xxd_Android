package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.webkit.WebViewClient;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutXiDuDetailActivity extends AppCompatActivity {
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
    public static void startAboutXiDuDetailActivity(Context context) {
        Intent intent = new Intent(context, AboutXiDuDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_xi_du_detail);
        ButterKnife.bind(this);
        tvTitle.setText("西都新闻详情");
        webrequest();
        initUI();
    }

    private void webrequest() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
//创建一个Request
        final Request request = new Request.Builder()
                .url("http://175.102.13.51:8080/XDSY/ZhuBan?type=.guanwang&defference=gongsi&indexPage=0\n")
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                Log.v("ffffff","5555555");
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                final String res = response.body().string();
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Log.v("sucess",res);

                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            int resultCode=jsonObject.getInt("flag");
                            if (resultCode==1){

                                JSONObject data=jsonObject.getJSONObject("data");
                                JSONObject ss=data.getJSONObject("gongsi");
                                URL=ss.toString();
                                Log.v("1111",ss.toString());

                            }else {
                                Log.v("fail","false");
                            }
                        }catch (Exception e){
                            Log.v("exception",e.toString());
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

    private void initUI() {
//        Log.v("55555",URL);
        //WebView加载web资源
        //xiduWebView.loadUrl("www.baidu.com");
        xiduWebView.loadData(URL,"text/html", "UTF-8");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        xiduWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
