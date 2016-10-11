package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.TeacherAdapter;
import com.xinxidu.xxd.base.Compares;
import com.xinxidu.xxd.netWork.TeacherBean;
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

public class TeacherTeamActivity1 extends AppCompatActivity {
    public static void startTeacherTeamActivity1(Context context) {
        Intent intent = new Intent(context, TeacherTeamActivity1.class);
        context.startActivity(intent);
    }

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    TeacherAdapter mTeacherAdapter;
    private ArrayList<TeacherBean> mItem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity);
        ButterKnife.bind(this);
        tvTitle.setText("名师团队");
        initNet();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initNet() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "list");
        map.put("name", "none");
        OkHttpUtils.get().url(Compares.TEACHER_URL).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(String response) {
                parseData(response);
                System.out.println(response);
            }
        });
    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject object = new JSONObject(response);
                JSONArray jsonArray = object.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String json = jsonArray.getString(i);
                    TeacherBean teacherBean = new Gson().fromJson(json, TeacherBean.class);
                    mItem.add(teacherBean);
                }
                mTeacherAdapter = new TeacherAdapter(this);
                mRecyclerView.setAdapter(mTeacherAdapter);
                mTeacherAdapter.setData(mItem);
                mTeacherAdapter.notifyDataSetChanged();
                mTeacherAdapter.setOnItemClickListener(new TeacherAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int postion) {
                        //
                        Intent intent = new Intent(getApplicationContext(), TeacherDetailsActivity.class);
                        intent.putExtra("name", mItem.get(postion).getId() + "");
                        intent.putExtra("image", mItem.get(postion).getImage());
                        intent.putExtra("name",mItem.get(postion).getName());
                        intent.putExtra("touxian",mItem.get(postion).getTouxian());
                        intent.putExtra("time",mItem.get(postion).getTime());
                        intent.putExtra("shanchang",mItem.get(postion).getShanchang());
                        intent.putExtra("jianjie",mItem.get(postion).getJianjie());
                        startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
