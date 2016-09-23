package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.DayInvestmentAdapter;
import com.xinxidu.xxd.adapter.ProfitSkillAdapter;
import com.xinxidu.xxd.adapter.XiduNewsAdapter;
import com.xinxidu.xxd.event.ProfitSkillEvent;
import com.xinxidu.xxd.event.XiduNewsEvent;
import com.xinxidu.xxd.models.XiduInfoData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiduNewsActivity extends Fragment {
    XiduNewsAdapter mXiduNewsAdapter;
    private ArrayList<XiduNewsEvent> mItem=new ArrayList<>();
    private RecyclerView mRecyclerView;
    protected static final String URL = "http://175.102.13.51:8080/XDSY/ZhuBan";
    private XiduNewsEvent event;
    public static void startProfitSkillActivity(Context context) {
        Intent intent = new Intent(context, XiduNewsActivity.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_xidu_news,container,false);
        ButterKnife.bind(this,view);
        webRequest();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void webRequest() {
        Map<String,String> map=new HashMap<>();
        map.put("type",".guanwang");
        map.put("defference","gonggao");
        map.put("indexPage","1");
        OkHttpUtils.get().url(URL).params(map).build().execute(new StringCallback() {

            @Override
            public void onBefore(okhttp3.Request request) {
                System.out.println("request=" + request.toString());
                super.onBefore(request);

            }

            @Override
            public void onError(okhttp3.Call call, Exception e) {
                System.out.println("e=" +e.toString());
            }

            @Override
            public void onResponse(String response) {
                 parseData(response);
            }
        });

    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)){
            try {
                JSONObject object=new JSONObject(response);
                if (object.getInt("flag")==1){
                    System.out.println("请求成功");
                    JSONArray data=object.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                      String json=data.getString(i);
                      System.out.println("json=" + json.toString());
                        Gson gson=new Gson();
                        event=gson.fromJson(json, XiduNewsEvent.class);
                        mItem.add(event);
                    }
                }else {
                    Log.v("fail","fail");
                }
                mXiduNewsAdapter = new XiduNewsAdapter(getActivity(),mItem);
                mRecyclerView.setAdapter(mXiduNewsAdapter);
                mXiduNewsAdapter.setOnItemClickListener(mOnItemClickListener);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    XiduNewsAdapter.OnItemClickListener mOnItemClickListener = new XiduNewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
           Intent intent = new Intent(getActivity(), XiduNewsDetailActivity.class);
            intent.putExtra("Id", mItem.get(position).getId());
            startActivity(intent);
        }
    };
}
