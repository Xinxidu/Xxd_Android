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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class BroadcastNewsFragment extends Fragment {

    private String[] tabTitle;
    @BindView(R.id.btoadcast_news_tabs)
    TabLayout btoadcastNewsTabs;
    @BindView(R.id.btoadcast_news_container)
    ViewPager btoadcastNewsContainer;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public BroadcastNewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast_news, container, false);
        ButterKnife.bind(this, view);
        tabTitle = getResources().getStringArray(R.array.tab_broadcast_news);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        btoadcastNewsContainer.setAdapter(mSectionsPagerAdapter);
        btoadcastNewsTabs.setupWithViewPager(btoadcastNewsContainer);
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
                    return new BroadcastNewsItemFragment();
                case 1:
                    return new BroadcastNewsBaseFragment();
                case 2:
                    return new BroadcastNewsBaseFragment();
                case 3:
                    return new BroadcastNewsBase2Fragment();
                case 4:
                    return new BroadcastNewsBaseFragment();
                case 5:
                    return new BroadcastNewsBaseFragment();
                case 6:
                    return new BroadcastNewsBaseFragment();
                case 7:
                    return new BroadcastNewsBaseFragment();
                case 8:
                    return new BroadcastNewsBase2Fragment();
                default:
                    return new BroadcastNewsBaseFragment();
            }
        }

        @Override
        public int getCount() {
            return 9;
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