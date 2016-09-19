package com.xinxidu.xxd.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.Suppress;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.AboutXiDuActivity;

import com.xinxidu.xxd.activity.HotActivity;
import com.xinxidu.xxd.activity.LoginActivity;
import com.xinxidu.xxd.activity.MyShiPanAccountActivity;
import com.xinxidu.xxd.activity.RegisterBasicActivity;
import com.xinxidu.xxd.event.UserLoginEvent;
import com.xinxidu.xxd.utils.CustomDialog;
import com.xinxidu.xxd.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/1.
 */

public class MyFragment extends Fragment {
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
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.btn_my_login)
    Button btnLogin;
    @BindView(R.id.btn_my_register)
    Button btnRegister;
    @BindView(R.id.rl_user_center)
    RelativeLayout rlUserCenter;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.my_collect)
    ImageView myCollect;
    @BindView(R.id.title_more)
    ImageView titleMore;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.my_account)
    RelativeLayout myAccount;
    @BindView(R.id.my_selection)
    RelativeLayout mySelection;
    @BindView(R.id.my_event)
    RelativeLayout myEvent;
    @BindView(R.id.my_aboutus)
    RelativeLayout myAboutus;
    @BindView(R.id.my_contactus)
    RelativeLayout myContactus;
    @BindView(R.id.my_help)
    RelativeLayout myHelp;
    @BindView(R.id.my_setting)
    RelativeLayout mySetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        tvTitle.setText("我的");
        back.setVisibility(View.GONE);
        return view;
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

    @OnClick({R.id.btn_my_login, R.id.btn_my_register, R.id.my_account, R.id.my_selection, R.id.my_event, R.id.my_aboutus, R.id.my_contactus, R.id.my_help, R.id.my_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_my_login:
                LoginActivity.startLoginActivity(getActivity());
                break;
            case R.id.btn_my_register:
                RegisterBasicActivity.startRegisterBasicActivity(getActivity());
                break;
            case R.id.my_account:
                MyShiPanAccountActivity.startMyShiPanAccountActivity(getActivity());
                break;
            case R.id.my_selection:
                break;
            case R.id.my_event:
                HotActivity.startHotActivity(getActivity());
                break;
            case R.id.my_aboutus:
                AboutXiDuActivity.startAboutXiDuActivity(getActivity());
                break;
            case R.id.my_contactus:
                showPhoneDialog();
                break;
            case R.id.my_help:
                break;
            case R.id.my_setting:
                break;
        }
    }

    @Subscribe
    public void onUserLoginEvent(UserLoginEvent event){
        tvUserName.setText(event.getUserName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

        @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void showPhoneDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("400-105-4080");
        builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "400-105-4080");
                intent.setData(data);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
