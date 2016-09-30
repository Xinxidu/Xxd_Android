package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xinxidu.xxd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsInfoActivity extends Fragment {

    protected static final String HOST = "http://175.102.13.51:8080/XDSY/ZhuBan?type=.guanwang&defference=lianxi&indexPage=0";
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
    @BindView(R.id.iv_address)
    ImageView ivAddress;

    public static void startyContactUsInfoActivity(Context context) {
        Intent intent = new Intent(context, ContactUsInfoActivity.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contact_us_info, container, false);
        webRequest();
        ButterKnife.bind(this, view);
        return view;
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("sucessuser", res);
                        try {
                            JSONObject json = new JSONObject(res);
                            int flag = json.getInt("flag");
                            String msg = json.getString("msg");
                            if (flag == 1) {
                                JSONArray dataArr = json.getJSONArray("data");
                                JSONObject data = (JSONObject) dataArr.opt(0);
                                Log.v("CompanyTel", data.getString("CompanyTel"));
                                CompanyTel.setText(data.getString("CompanyTel"));
                                CustomerHotline.setText(data.getString("CustomerHotline"));
                                JoinHotline.setText(data.getString("JoinHotline"));
                                CompanyAddress.setText(data.getString("CompanyAddress"));
                                ComplaintsTel.setText(data.getString("ComplaintsTel"));
                               // String HeadquartersAddress=data.getString("HeadquartersAddress");
                               // Log.v("HeadquartersAddress",HeadquartersAddress);
                                String url="http://www.xiduoil.com/uploads/150310/1-150310103006241.png";
                                //得到可用的图片
                                //Bitmap bitmap = getHttpBitmap(url);
                                //ivAddress.setImageBitmap(bitmap);
                              // Bitmap bitmap = getLocalBitmap("/Users/aaronlee/Desktop/XXD_Android/app/src/main/res/drawable-hdpi/aaa.png");
                               // ivAddress.setImageBitmap(bitmap);

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                });

            }
        });
    }

    /** 
     * 加载本地图片 
     * http://bbs.3gstdy.com * @param url * @return */

    public static Bitmap getLocalBitmap(String url){
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL = null;
        Bitmap bitmap=null;
        try{
            //Log.v("url",url);
            myFileURL = new URL(url);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        try {
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(3000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

}

