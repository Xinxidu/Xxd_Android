package com.xinxidu.xxd.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class XiduNewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wv_xidunews)
    WebView wvXidunews;
    protected static final String URL="http://175.102.13.51:8080/XDSY/Detail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xidu_news_detail);
        ButterKnife.bind(this);
        tvTitle.setText("西都新闻详情");
        LoadUI();
    }

    private void LoadUI() {
        Map<String,String> map=new HashMap<>();
        map.put("id","11319");
        map.put("type","OfficialDto");
        OkHttpUtils.get().url(URL).params(map).build().execute(new StringCallback() {
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
        if (!TextUtils.isEmpty(response)){
            try {
                JSONObject object=new JSONObject(response);
                if (object.getInt("flag")==1){
                    System.out.println("成功========");
                    JSONArray data=object.getJSONArray("data");
                    JSONObject json=data.getJSONObject(0);
                    wvXidunews.loadDataWithBaseURL(null,json.getString("body"),"text/html","utf-8",null);
                }

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
