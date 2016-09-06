package com.xinxidu.xxd.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.base.App;
import com.xinxidu.xxd.bean.Engine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFagment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private BGABanner mDefaultBanner;
    private List<View> mDefaultViews;
    private Engine mEngine;
    private TextView news,assess;
    private TabLayout mTabs;
    private ViewPager mContainer;
    private String[] tabTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mEngine = App.getInstance().getEngine();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tabTitle = getResources().getStringArray(R.array.tab_task);
        mTabs= (TabLayout) view.findViewById(R.id.tabs);
        mContainer= (ViewPager) view.findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        mContainer.setAdapter(mSectionsPagerAdapter);
        mTabs.setupWithViewPager(mContainer);
        initDefault(view);
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
                    return new TimeNewsFragment();
                default:
                    return new BullionCommentFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

    //viewpager 图片轮播
    private void initDefault(View view) {
        mDefaultBanner = (BGABanner) view.findViewById(R.id.banner_main_default);
        mDefaultViews = getViews(4);
        mDefaultBanner.setViews(mDefaultViews);

       // BannerModel bannerModel = new BannerModel();
        List<String> imgs = new ArrayList<String>();
        imgs.add("http://120.25.164.72/resources/index_banner.png");
        imgs.add("http://120.25.164.72/resources/index_banner.png");
        imgs.add("http://120.25.164.72/resources/index_banner.png");
        imgs.add("http://120.25.164.72/resources/index_banner.png");

        List<String> tips = new ArrayList<String>();
        tips.add("西都");
        tips.add("西都");
        tips.add("西都");
        tips.add("西都");
        tips.add("西都");

                SimpleDraweeView simpleDraweeView;
                for (int i = 0; i < mDefaultViews.size(); i++) {
                    simpleDraweeView = (SimpleDraweeView) mDefaultViews.get(i);
                    simpleDraweeView.setImageURI(Uri.parse(imgs.get(i)));

                    // 为每一页添加点击事件
                    final int finalPosition = i;
                    simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

//                            Intent intent = new Intent(getActivity(), ProductListActivity.class);
//                            getActivity().startActivity(intent);
                        }
                    });
                }
                mDefaultBanner.setTips(tips);
    }

    private List<View> getViews(int count) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            views.add(getActivity().getLayoutInflater().inflate(R.layout.view_image, null));
        }
        return views;
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


