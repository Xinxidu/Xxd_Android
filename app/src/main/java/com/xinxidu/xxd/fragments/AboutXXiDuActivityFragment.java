package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.AboutXiDuDetailActivity;
import com.xinxidu.xxd.activity.ContactUsInfoActivity;
import com.xinxidu.xxd.activity.ExchangeInfoActivity;
import com.xinxidu.xxd.activity.XiduNewsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AboutXXiDuActivityFragment extends Fragment {

    @BindView(R.id.trade_tabs)
    TabLayout tradeTabs;
    @BindView(R.id.tarde_container)
    ViewPager tardeContainer;
    private String[] tabTitle;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public AboutXXiDuActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_xxi_du, container, false);
        ButterKnife.bind(this, view);
        tabTitle = getResources().getStringArray(R.array.tab_xidu);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
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
                    return new ExchangeInfoActivity();
                case 1:
                    return new AboutXiDuDetailActivity();
                case 2:
                    return new XiduNewsActivity();
                default:
                    return new ContactUsInfoActivity();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }
}
