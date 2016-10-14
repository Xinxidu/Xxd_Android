package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/29.
 */
public class LiveTelecastURLActivity extends Activity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void startLiveTelecastURLActivityy(Context context) {
        Intent intent = new Intent(context, LiveTelecastURLActivity.class);
        context.startActivity(intent);
    }


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_url);
        ButterKnife.bind(this);
        tvTitle.setText("直播视频");
        init();
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        // 设置支持js，默认为false
        webView.getSettings().setJavaScriptEnabled(true);
        //WebView加载web资源
        webView.loadUrl("http://win.xxidu.com/");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
