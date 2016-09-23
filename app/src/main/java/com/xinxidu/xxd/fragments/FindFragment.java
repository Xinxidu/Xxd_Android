package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.AtionMessageActivity;
import com.xinxidu.xxd.activity.DayInvestmentActivity;
import com.xinxidu.xxd.activity.LiveRoomActivity;
import com.xinxidu.xxd.activity.ProfitSkillActivity;
import com.xinxidu.xxd.activity.TeacherTeamActivity;

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
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.find_online)
    TextView findOnline;
    @BindView(R.id.find_investment)
    TextView findInvestment;
    @BindView(R.id.find_profit)
    TextView findProfit;
    @BindView(R.id.find_teacherteam)
    TextView findTeacherteam;
    @BindView(R.id.find_trade)
    TextView findTrade;

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

    @OnClick({R.id.title_name, R.id.find_online, R.id.find_investment, R.id.find_profit, R.id.find_teacherteam, R.id.find_trade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_name:
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
                TeacherTeamActivity.startTeacherTeamActivity(getActivity());
                break;
            case R.id.find_trade:
                break;
        }
    }
}
