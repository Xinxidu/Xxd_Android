package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.ProfitSkillAdapter;
import com.xinxidu.xxd.adapter.XiduNewsAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.event.ProfitSkillEvent;
import com.xinxidu.xxd.event.XiduNewsEvent;
import com.xinxidu.xxd.netWork.ProfitSkillBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfitSkillActivity extends AppCompatActivity {
    ProfitSkillAdapter mProfitSkillAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ArrayList<ProfitSkillBean> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipe_refresh;
    public static void startProfitSkillActivity(Context context) {
        Intent intent = new Intent(context, ProfitSkillActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_skill);
        ButterKnife.bind(this);
        tvTitle.setText("盈利技巧");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_profit);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //网络请求
        webRequest();
        //刷新
        refresh();
    }

    private void refresh() {
        swipe_refresh.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent, R.color.primary_text);
        swipe_refresh.measure(0, 0);
        swipe_refresh.setRefreshing(false);
        //设置刷新监听
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh.setRefreshing(false);
                        webRequest();
                        mItem.clear();
                    }
                }, 3000);
            }
        });
    }

    private void webRequest() {
        Map<String, String> map = new HashMap<>();
        map.put("type", ".guanwang");
        map.put("defference", "touzi");
        map.put("indexPage", "1");
        map.put("pageRows", "10");
        OkHttpUtils.get().url(Compares.ZHUBAN_URL).params(map).build().execute(new StringCallback() {

            @Override
            public void onBefore(okhttp3.Request request) {
                System.out.println("request=" + request.toString());
                super.onBefore(request);

            }

            @Override
            public void onError(okhttp3.Call call, Exception e) {
                System.out.println("e=" + e.toString());
            }

            @Override
            public void onResponse(String response) {
                parseData(response);
            }
        });
    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getInt("flag") == 1) {
                    System.out.println("请求成功");
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        String json = data.getString(i);
                        System.out.println("json=" + json.toString());
                        ProfitSkillBean bean = new Gson().fromJson(json, ProfitSkillBean.class);
                        mItem.add(bean);
                    }
                } else {
                    Log.v("fail", "fail");
                }
                mProfitSkillAdapter = new ProfitSkillAdapter(this, mItem);
                mRecyclerView.setAdapter(mProfitSkillAdapter);
                mProfitSkillAdapter.setOnItemClickListener(mOnItemClickListener);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    XiduNewsAdapter.OnItemClickListener mOnItemClickListener = new XiduNewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(ProfitSkillActivity.this, XiduNewsDetailActivity.class);
            intent.putExtra("Id", mItem.get(position).getId());
            startActivity(intent);
        }
    };

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
