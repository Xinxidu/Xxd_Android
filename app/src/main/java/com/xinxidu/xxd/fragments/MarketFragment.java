package com.xinxidu.xxd.fragments;

import android.content.Intent;
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
import com.xinxidu.xxd.activity.ElectricTradeActivity;

/**
 * Created by limingquan on 2016/9/1.
 */
public class MarketFragment extends Fragment {

    private String[] tabTitle;
    private TabLayout mTabs;
    private ViewPager mContainer;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.market_fragment,container,false);
        tabTitle = getResources().getStringArray(R.array.tab_market);
        mTabs= (TabLayout) view.findViewById(R.id.market_tabs);
        mContainer= (ViewPager) view.findViewById(R.id.market_container);
        RelativeLayout back= (RelativeLayout) view.findViewById(R.id.back);
        RelativeLayout back1= (RelativeLayout) view.findViewById(R.id.back1);
        TextView tv_title= (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText("行情");

        back.setVisibility(View.GONE);
        back1.setVisibility(View.VISIBLE);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        mContainer.setAdapter(mSectionsPagerAdapter);
        mTabs.setupWithViewPager(mContainer);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ElectricTradeActivity.startElectricTradeActivity(getActivity());
            }
        });
        return view;
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
//                case 0:
//                    return new TimeNewsFragment();
                default:
                    return new MarketItemFragment();
            }
        }

        @Override
        public int getCount() {
            return 12;
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
