package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.DayInvestmentAdapter;
import com.xinxidu.xxd.event.DayInvestmentEvent;
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
import okhttp3.Call;
import okhttp3.Request;

public class DayInvestmentActivity extends Activity {

    DayInvestmentAdapter mDayInvestmentAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ArrayList<DayInvestmentEvent> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;
    public static final String HOME_URL = "http://175.102.13.51:8080/XDSY/ZhuBan";
    private DayInvestmentEvent event;

    public static void startDayInvestmentActivity(Context context) {
        Intent intent = new Intent(context, DayInvestmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_investment);
        ButterKnife.bind(this);
        tvTitle.setText("每日投资策略");
        initGetNet();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void initGetNet() {

        Map<String, String> map = new HashMap<>();
        map.put("type", ".guanwang");
        map.put("defference", "hangqing");
        map.put("indexPage", "1");
        OkHttpUtils.get().url(HOME_URL).params(map).build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request) {
                System.out.println("request=" + request.toString());
                super.onBefore(request);

            }

            @Override
            public void onError(Call call, Exception e) {
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
                    System.out.println("执行");
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        String json = data.getString(i);
                        System.out.println("json=" + json.toString());
                        Gson gson = new Gson();
                        event = gson.fromJson(json, DayInvestmentEvent.class);
                        mItem.add(event);

                    }
                }
                mDayInvestmentAdapter = new DayInvestmentAdapter(this, mItem);
                mRecyclerView.setAdapter(mDayInvestmentAdapter);
                mDayInvestmentAdapter.setOnItemClickListener(mOnItemClickListener);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    DayInvestmentAdapter.OnItemClickListener mOnItemClickListener = new DayInvestmentAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(DayInvestmentActivity.this, DayInvestmentDetailActivity.class);
//            intent.putExtra("description", mItem.get(position).getDescription());
//            intent.putExtra("body", mItem.get(position).getBody());
            startActivity(intent);
        }
    };

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
