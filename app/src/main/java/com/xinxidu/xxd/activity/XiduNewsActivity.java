package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.ProfitSkillAdapter;
import com.xinxidu.xxd.adapter.XiduNewsAdapter;
import com.xinxidu.xxd.event.ProfitSkillEvent;
import com.xinxidu.xxd.event.XiduNewsEvent;
import com.xinxidu.xxd.models.XiduInfoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiduNewsActivity extends AppCompatActivity {
    XiduNewsAdapter mXiduNewsAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayList<XiduNewsEvent> mItem;
    private RecyclerView mRecyclerView;
    protected static final String URL = "http://175.102.13.51:8080/XDSY/ZhuBan?type=.guanwang&defference=gonggao&indexPage=1";

    public static void startProfitSkillActivity(Context context) {
        Intent intent = new Intent(context, XiduNewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xidu_news);
        ButterKnife.bind(this);
        tvTitle.setText("西都新闻");
        webRequest();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<XiduNewsEvent>();
        mItem.add(null);
//        if (mItem != null && mItem.size() > 0) {
        mXiduNewsAdapter = new XiduNewsAdapter(this);
        mRecyclerView.setAdapter(mXiduNewsAdapter);
        mXiduNewsAdapter.setData(mItem);
        mXiduNewsAdapter.notifyDataSetChanged();
    }

    private void webRequest() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("sucessnews", res);
                        try {
                            JSONObject json=new JSONObject(res);
                            int flag=json.getInt("flag");
                            String msg=json.getString("msg");
                            if (flag==1){
                                JSONArray dataArr=json.getJSONArray("data");
                                JSONObject data=(JSONObject)dataArr.opt(0);
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
