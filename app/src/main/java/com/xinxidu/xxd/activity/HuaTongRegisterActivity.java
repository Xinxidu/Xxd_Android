package com.xinxidu.xxd.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.utils.WebView.ImagePick;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuaTongRegisterActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wv_reg)
    WebView wvReg;

    private ValueCallback<Uri> mFilePathCallback;
    private ValueCallback<Uri[]> mFilePathCallbackArray;
    private ImagePick ip;

    public static void startHuaTongRegisterActivity(Context context) {
        Intent intent = new Intent(context, HuaTongRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_tong_register);
        ButterKnife.bind(this);
        tvTitle.setText("基本信息");
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        //允许JavaScript执行
        wvReg.getSettings().setJavaScriptEnabled(true);
        wvReg.getSettings().setLoadsImagesAutomatically(true);
        wvReg.setVerticalScrollBarEnabled(false);
        //运行webview通过URI获取安卓文件
        wvReg.getSettings().setAllowFileAccess(true);
        wvReg.getSettings().setAllowFileAccessFromFileURLs(true);
        wvReg.getSettings().setAllowUniversalAccessFromFileURLs(true);

//        wvReg.setWebChromeClient(new MyWebChromeClient());//设置可以打开图片管理器

        wvReg.getSettings().setJavaScriptEnabled(true);
        wvReg.loadUrl("http://trade.huatongsilver.com:80/accountweb/web/reg2/reg.html");


        wvReg.setWebChromeClient(new WebChromeClient() {
            // file upload callback (Android 2.2 (API level 8) -- Android 2.3 (API level 10)) (hidden method)
            public void openFileChooser(ValueCallback<Uri> filePathCallback) {
                handle(filePathCallback);
            }

            // file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15)) (hidden method)
            public void openFileChooser(ValueCallback filePathCallback, String acceptType) {
                handle(filePathCallback);
            }

            // file upload callback (Android 4.1 (API level 16) -- Android 4.3 (API level 18)) (hidden method)
            public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture) {
                handle(filePathCallback);
            }

            // for Lollipop
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                // Double check that we don't have any existing callbacks
                if (mFilePathCallbackArray != null) {
                    mFilePathCallbackArray.onReceiveValue(null);
                }
                mFilePathCallbackArray = filePathCallback;
                showDialog();
                return true;
            }

            /**
             * 处理5.0以下系统回调
             * @param filePathCallback
             */
            private void handle(ValueCallback<Uri> filePathCallback) {
                if (filePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;
                showDialog();
            }

            /**
             * 显示照片选取Dialog
             */
            public void showDialog() {
                if (ip == null) {
                    ip = new ImagePick(HuaTongRegisterActivity.this);
                }
                ip.setCancel(new ImagePick.MyDismiss() {
                    @Override
                    public void dismiss() {
                        handleCallback(null);
                    }
                });
                ip.show();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) { // 正常照片选取的时候调用
            ip.onActivityResult(requestCode, resultCode, data, new ImagePick.MyUri() {
                @Override
                public void getUri(Uri uri) {
                    handleCallback(uri);
                }
            });
        } else {
            // 取消了照片选取的时候调用
            handleCallback(null);
        }
    }

    /**
     * 处理WebView的回调
     * @param uri
     */
    private void handleCallback(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mFilePathCallbackArray != null) {
                if (uri != null) {
                    mFilePathCallbackArray.onReceiveValue(new Uri[]{uri});
                } else {
                    mFilePathCallbackArray.onReceiveValue(null);
                }
                mFilePathCallbackArray = null;
            }
        } else {
            if (mFilePathCallback != null) {
                if (uri != null) {
                    mFilePathCallback.onReceiveValue(uri);
                } else {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = null;
            }
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
