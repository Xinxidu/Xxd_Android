package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.models.XiduInfoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsInfoActivity extends AppCompatActivity {
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
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.CompanyTel)
    TextView CompanyTel;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.CustomerHotline)
    TextView CustomerHotline;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.JoinHotline)
    TextView JoinHotline;
    @BindView(R.id.textView44)
    TextView textView44;
    @BindView(R.id.ComplaintsTel)
    TextView ComplaintsTel;
    @BindView(R.id.textView55)
    TextView textView55;
    @BindView(R.id.CompanyAddress)
    TextView CompanyAddress;
    protected static final String HOST = "http://175.102.13.51:8080/XDSY/ZhuBan?type=.guanwang&defference=lianxi&indexPage=0";
    public static void startyContactUsInfoActivity(Context context) {
        Intent intent = new Intent(context, ContactUsInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_info);
        ButterKnife.bind(this);
        tvTitle.setText("联系我们");
        webRequest();
    }

    private void webRequest() {
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
                        Log.v("sucessuser", res);
                                try {
                                    JSONObject json=new JSONObject(res);
                                    int flag=json.getInt("flag");
                                    String msg=json.getString("msg");
                                    if (flag==1){
                                        JSONArray dataArr=json.getJSONArray("data");
                                        JSONObject data=(JSONObject)dataArr.opt(0);
                                         CompanyTel.setText(data.getString("CompanyTel"));
                                         CustomerHotline.setText(data.getString("CustomerHotline"));
                                         JoinHotline.setText(data.getString("JoinHotline"));
                                         CompanyAddress.setText(data.getString("CompanyAddress"));
                                         ComplaintsTel.setText(data.getString("ComplaintsTel"));
                                      //  String HeadquartersAddress=data.getString("HeadquartersAddress");
                                    }
                                }catch (JSONException e){
                                    throw new RuntimeException(e);
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
