package com.xinxidu.xxd.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.activity.BroadcastNewsActivity;
import com.xinxidu.xxd.activity.FinanceCalendarActivity;
import com.xinxidu.xxd.activity.HotActivity;
import com.xinxidu.xxd.activity.HotTradeActivity;
import com.xinxidu.xxd.activity.HuaTongLoginActivity;
import com.xinxidu.xxd.base.App;
import com.xinxidu.xxd.event.Engine;
import com.xinxidu.xxd.viewpager.bean.ADInfo;
import com.xinxidu.xxd.viewpager.lib.CycleViewPager;
import com.xinxidu.xxd.viewpager.utils.ViewFactory;

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
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private Engine mEngine;
    private ViewPager mContainer;
    private String[] tabTitle;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;


    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg"};

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
        cycleViewPager=new CycleViewPager();
//        FragmentManager fragmentv=(FragmentManager) view.findViewById(R.id.fragment_cycle_viewpager_content);
        tvTitle.setText("主页");
        back.setVisibility(View.GONE);
        configImageLoader();
        initialize();
        return view;
    }

    @SuppressLint("NewApi")
    private void initialize() {
        cycleViewPager = (CycleViewPager) getChildFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);
        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
            }

        }

    };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }



    @OnClick({R.id.bt_trade, R.id.bt_activity, R.id.bt_news, R.id.bt_calendar, R.id.bt_now_open, R.id.bt_live_telecast})
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
                String url = "http://win.xxidu.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
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
//    private void initDefault(View view) {
//        mDefaultBanner = (BGABanner) view.findViewById(R.id.banner_main_default);
//        mDefaultViews = getViews(4);
//        mDefaultBanner.setViews(mDefaultViews);
//
//        // BannerModel bannerModel = new BannerModel();
//        List<String> imgs = new ArrayList<String>();
//        imgs.add("http://www.tooopen.com/view/802192.html");
//        imgs.add("http://www.tooopen.com/view/802192.html");
//        imgs.add("http://www.tooopen.com/view/802192.html");
//        imgs.add("http://www.tooopen.com/view/802192.html");
//        Log.d("imgs", "sdfsdfsd");
//
//        List<String> tips = new ArrayList<String>();
//        tips.add("");
//        tips.add("");
//        tips.add("");
//        tips.add("");
//        tips.add("");
//
//        SimpleDraweeView simpleDraweeView;
//        for (int i = 0; i < mDefaultViews.size(); i++) {
//            simpleDraweeView = (SimpleDraweeView) mDefaultViews.get(i);
//            simpleDraweeView.setImageURI(Uri.parse(imgs.get(i)));
//
//            // 为每一页添加点击事件
//            final int finalPosition = i;
//            simpleDraweeView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
////                            Intent intent = new Intent(getActivity(), ProductListActivity.class);
////                            getActivity().startActivity(intent);
//                }
//            });
//        }
//        mDefaultBanner.setTips(tips);
//    }

//    private List<View> getViews(int count) {
//        List<View> views = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            views.add(getActivity().getLayoutInflater().inflate(R.layout.view_image, null));
//        }
//        return views;
//    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

}


