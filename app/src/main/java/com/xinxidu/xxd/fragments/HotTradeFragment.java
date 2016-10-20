package com.xinxidu.xxd.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.LoginActivity;
import com.xinxidu.xxd.adapter.HotTradeAdapter;
import com.xinxidu.xxd.netWork.HotTradeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotTradeFragment extends Fragment {

    @BindView(R.id.ok)
    RelativeLayout ok;
    private RecyclerView mRecyclerView;
    private Spinner tvDownUp;
    private Spinner tvVolume;

    HotTradeAdapter mHotTradeAdapter;
    private ArrayAdapter<String> arr_adapter;
    private ArrayList<HotTradeBean> mItem = new ArrayList<>();

    public static final String URL_PAY = "http://192.168.73.67:888/XDSY/latestInformation";

    public HotTradeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_trade, container, false);
        initNet();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        tvDownUp = (Spinner) view.findViewById(R.id.tv_down_up);
        tvVolume = (Spinner) view.findViewById(R.id.tv_volume);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        spinner1();
        spinner2();
        ButterKnife.bind(this, view);
        ok.setVisibility(View.VISIBLE);
        return view;
    }

    private void initNet() {
        OkHttpUtils.get()
                .url(URL_PAY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("e=" + e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        parseData(response);

                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        System.out.println(request);
                    }
                });
    }

    private void parseData(String response) {
        System.out.println("123456=" + response);
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    String json = array.getString(i);
                    mItem.add(new Gson().fromJson(json, HotTradeBean.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mHotTradeAdapter = new HotTradeAdapter(getActivity());
            mRecyclerView.setAdapter(mHotTradeAdapter);
            mHotTradeAdapter.setData(mItem);
            mHotTradeAdapter.notifyDataSetChanged();
            //点击回调穿id
            mHotTradeAdapter.setOnItemClickListener(new HotTradeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        intent.putExtra("id", mItem.get(position).getId() + "");
                    getActivity().startActivity(intent);
                }
            });
        }

    }

    private void spinner1() {
        final String[] data_list = {"涨跌幅", "振幅"};
        arr_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner1_trade, data_list);
        arr_adapter.setDropDownViewResource(R.layout.spinner_trade);
        tvDownUp.setAdapter(arr_adapter);
        tvDownUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        mHotTradeAdapter.setIsMarket(false);
                        break;
                    case 1:
                        mHotTradeAdapter.setIsMarket(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spinner2() {
        final String[] data_list = {"最高", "最低", "开盘", "昨收", "昨结", "现量", "持货量", "成交额", "成交量"};
        arr_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner1_trade, data_list);
        arr_adapter.setDropDownViewResource(R.layout.spinner_trade);
        tvVolume.setAdapter(arr_adapter);
        tvVolume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 1:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 2:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 3:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 4:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 5:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 6:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 7:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                    case 8:
                        mHotTradeAdapter.setItemPosition(position);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
