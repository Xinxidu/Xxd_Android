package com.xinxidu.xxd.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.HotActivity;
import com.xinxidu.xxd.activity.LoginActivity;
import com.xinxidu.xxd.activity.MyAccountInfoActivity;
import com.xinxidu.xxd.activity.MyShiPanAccountActivity;
import com.xinxidu.xxd.activity.RegisterBasicActivity;
import com.xinxidu.xxd.activity.AboutXXiDuActivity;
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
    @BindView(R.id.tv_username)
    TextView tvUserName;
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
    @BindView(R.id.btn_my_login)
    TextView btnMyLogin;
    @BindView(R.id.btn_my_register)
    TextView btnMyRegister;
    @BindView(R.id.btn_back_login)
    Button btnBackLogin;
    @BindView(R.id.rl_user_center)
    RelativeLayout rlUserCenter;
    @BindView(R.id.my_account)
    TextView myAccount;
    @BindView(R.id.my_selection)
    TextView mySelection;
    @BindView(R.id.my_event)
    TextView myEvent;
    @BindView(R.id.my_aboutus)
    TextView myAboutus;
    @BindView(R.id.my_contactus)
    TextView myContactus;
    @BindView(R.id.my_help)
    TextView myHelp;
    @BindView(R.id.my_setting)
    TextView mySetting;

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

    @OnClick({R.id.btn_my_login, R.id.btn_my_register, R.id.btn_back_login, R.id.my_account, R.id.my_selection,
            R.id.my_event, R.id.my_aboutus, R.id.my_contactus, R.id.my_help, R.id.my_setting,R.id.rl_user_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user_center:
                MyAccountInfoActivity.startMyAccountInfoActivity(getActivity());
                break;
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
            case R.id.btn_back_login:
//                LoginActivity.startLoginActivity(getActivity());
                tvUserName.setText("未登录");
                btnMyRegister.setVisibility(View.VISIBLE);
                btnMyLogin.setVisibility(View.VISIBLE);
                btnBackLogin.setVisibility(View.GONE);
                break;
            case R.id.my_event:
                HotActivity.startHotActivity(getActivity());
                break;
            case R.id.my_aboutus:
                AboutXXiDuActivity.startAboutXXiDuActivity(getActivity());
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
    public void onUserLoginEvent(UserLoginEvent event) {
//            tvUserName.setText(event.getUserName());
        boolean name = true;

        if (name) {
            tvUserName.setText("已登录");
            btnMyRegister.setVisibility(View.GONE);
            btnMyLogin.setVisibility(View.GONE);
            btnBackLogin.setVisibility(View.VISIBLE);
        } else {
            tvUserName.setText("未登录");
            btnMyRegister.setVisibility(View.VISIBLE);
            btnMyLogin.setVisibility(View.VISIBLE);
            btnBackLogin.setVisibility(View.GONE);
        }

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
