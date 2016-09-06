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

import com.xinxidu.xxd.R;

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
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        mContainer.setAdapter(mSectionsPagerAdapter);
        mTabs.setupWithViewPager(mContainer);
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
                    return new TimeNewsFragment();
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
}
