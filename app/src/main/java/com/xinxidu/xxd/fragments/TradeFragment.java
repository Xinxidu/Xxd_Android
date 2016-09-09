package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by limingquan on 2016/9/1.
 * 交易  华通登录接口，需要判断是否登录，是否有账号
 */
public class TradeFragment extends Fragment {

    private String[] tabTitle;
    private SectionsPagerAdapter mSectionsPagerAdapter;
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
    @BindView(R.id.trade_tabs)
    TabLayout tradeTabs;
    @BindView(R.id.tarde_container)
    ViewPager tardeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //登陆layout
//        View view = inflater.inflate(R.layout.hua_tong_lagin_activity, container, false);
        View view = inflater.inflate(R.layout.fragment_trade, container, false);
        ButterKnife.bind(this, view);
        tabTitle = getResources().getStringArray(R.array.tab_trade);
        back.setVisibility(View.GONE);
        tvTitle.setText("实盘交易");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        tardeContainer.setAdapter(mSectionsPagerAdapter);
        tradeTabs.setupWithViewPager(tardeContainer);
        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BuyIntoFragment();
                case 1:
                    return new SellIntoFragment();
                case 2:
                    return new CycBuyFragment();
                case 3:
                    return new CycSellFragment();
                case 4:
                    return new EntrustFragment();
                case 5:
                    return new HoldGoodsGragment();
                default:
                    return new HoldGoodsGragment();
            }
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }
}
