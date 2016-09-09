package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.AtionMessageActivity;
import com.xinxidu.xxd.activity.DayInvestmentActivity;
import com.xinxidu.xxd.activity.LiveRoomActivity;
import com.xinxidu.xxd.activity.ProfitSkillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindFragment extends Fragment {
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
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.find_eventnews)
    RelativeLayout findEventnews;
    @BindView(R.id.title_more)
    ImageView titleMore;
    @BindView(R.id.find_online)
    RelativeLayout findOnline;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.find_investment)
    RelativeLayout findInvestment;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.find_profit)
    RelativeLayout findProfit;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.find_teacherteam)
    RelativeLayout findTeacherteam;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.find_trade)
    RelativeLayout findTrade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        ButterKnife.bind(this, view);
        tvTitle.setText("发现");
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

    @OnClick({R.id.find_eventnews, R.id.find_online, R.id.find_investment, R.id.find_profit, R.id.find_teacherteam, R.id.find_trade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_eventnews:
                AtionMessageActivity.startAtionMessageActivity(getActivity());
                break;
            case R.id.find_online:
                LiveRoomActivity.startLiveRoomActivity(getActivity());
                break;
            case R.id.find_investment:
                DayInvestmentActivity.startDayInvestmentActivity(getActivity());
                break;
            case R.id.find_profit:
                ProfitSkillActivity.startProfitSkillActivity(getActivity());
                break;
            case R.id.find_teacherteam:
                break;
            case R.id.find_trade:
                break;
        }
    }
}
