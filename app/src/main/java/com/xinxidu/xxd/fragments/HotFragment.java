package com.xinxidu.xxd.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.LoginActivity;
import com.xinxidu.xxd.adapter.HotActivityAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.netWork.HotActivityBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotFragment extends Fragment {
    HotActivityAdapter mHotActivityAdapter;

    private ArrayList<HotActivityBean> mItem = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public HotFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        //访问网络
        initNet();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void initNet() {
        //集合用于存接口字段
        Map<String, String> map = new HashMap<>();
        //字段
        map.put("status", "");
        map.put("currentPage", "1");
        map.put("pageSize", "");
        OkHttpUtils.get()
//        OkHttpUtils.post()
                .url(Compares.HOT_ACTIVITY_URL)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e) {
//                        System.out.println(e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        parseData(response);
//                        System.out.println(response);
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        System.out.println(request);
                    }
                });
    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)) {//http://app.service.xiduoil.com/app/controller/avtive/query/json?status=1&currentPage=1&pageSize=1
            try {
                JSONObject object = new JSONObject(response);
                JSONArray jsonArray = object.getJSONArray("resultList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String json = jsonArray.getString(i);
                    HotActivityBean sportBean = new Gson().fromJson(json, HotActivityBean.class);
                    mItem.add(sportBean);
                }
                mHotActivityAdapter = new HotActivityAdapter(getActivity());
                mRecyclerView.setAdapter(mHotActivityAdapter);
                mHotActivityAdapter.setData(mItem);
                mHotActivityAdapter.notifyDataSetChanged();
                //点击回调穿id
                mHotActivityAdapter.setOnItemClickListener(new HotActivityAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra("id", mItem.get(position).getId() + "");
                        getActivity().startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
