package com.xinxidu.xxd.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.BroadcastNewsActivity;
import com.xinxidu.xxd.activity.FinanceCalendarActivity;
import com.xinxidu.xxd.activity.HotActivity;
import com.xinxidu.xxd.activity.HotTradeActivity;
import com.xinxidu.xxd.activity.HuaTongLoginActivity;
import com.xinxidu.xxd.base.App;
import com.xinxidu.xxd.event.Engine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFagment extends Fragment {

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
    @BindView(R.id.banner_main_default)
    BGABanner bannerMainDefault;
    @BindView(R.id.bt_trade1)
    ImageView btTrade1;
    @BindView(R.id.bt_trade)
    RelativeLayout btTrade;
    @BindView(R.id.bt_activity1)
    ImageView btActivity1;
    @BindView(R.id.bt_activity)
    RelativeLayout btActivity;
    @BindView(R.id.bt_news1)
    ImageView btNews1;
    @BindView(R.id.bt_news)
    RelativeLayout btNews;
    @BindView(R.id.bt_calendar1)
    ImageView btCalendar1;
    @BindView(R.id.bt_calendar)
    RelativeLayout btCalendar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.bt_now_open)
    Button btNowOpen;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.bt_live_telecast)
    Button btLiveTelecast;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private BGABanner mDefaultBanner;
    private List<View> mDefaultViews;
    private Engine mEngine;
    private TextView news, assess;
    private ViewPager mContainer;
    private String[] tabTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mEngine = App.getInstance().getEngine();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        tabTitle = getResources().getStringArray(R.array.tab_task);
        mContainer = (ViewPager) view.findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());//getSupportFragmentManager()
        mContainer.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(mContainer);
        initDefault(view);
        tvTitle.setText("主页");
        back.setVisibility(View.GONE);
        return view;
    }

    @OnClick({R.id.bt_trade, R.id.bt_activity, R.id.bt_news, R.id.bt_calendar, R.id.bt_now_open, R.id.bt_live_telecast, R.id.iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_trade:
                HotTradeActivity.startHotTradeActivity(getActivity());
                break;
            case R.id.bt_activity:
                HotActivity.startHotActivity(getActivity());
                break;
            case R.id.bt_news:
                BroadcastNewsActivity.startBroadcastNewsActivity(getActivity());
                break;
            case R.id.bt_calendar:
                FinanceCalendarActivity.startFinanceCalendarActivity(getActivity());
                break;
            case R.id.bt_now_open:
                HuaTongLoginActivity.startHuaTongLoginActivity(getActivity());
                break;
            case R.id.bt_live_telecast:
                break;
            case R.id.iv_more:
                break;
        }
    }

    //TabLayout+2个fragment实时快讯+金银牛评
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
        imgs.add("http://www.tooopen.com/view/802192.html");
        imgs.add("http://www.tooopen.com/view/802192.html");
        imgs.add("http://www.tooopen.com/view/802192.html");
        imgs.add("http://www.tooopen.com/view/802192.html");
        Log.d("imgs", "sdfsdfsd");

        List<String> tips = new ArrayList<String>();
        tips.add("");
        tips.add("");
        tips.add("");
        tips.add("");
        tips.add("");

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


