package com.xinxidu.xxd.fragments.marketunscramble;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.fragments.BullionCommentFragment;
import com.xinxidu.xxd.fragments.TimeNewsFragment;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarketUnscrambleActivity extends AppCompatActivity {
    @BindArray(R.array.tab_car)
    String[] tabTitle;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static void startMarketUnscrambleActivity(Context context) {
        Intent intent = new Intent(context, MarketUnscrambleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_unscramble);
        ButterKnife.bind(this);
        tvTitle.setText("行情解读");

        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());//getSupportFragmentManager()
        container.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(container);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MarketUnscrambleFragment.newInstance("free", MarketUnscrambleDetailsActivity.class);
                case 1:
                    return MarketUnscrambleFragment.newInstance("busy", MarketUnscrambleDetailsActivity.class);
                default:
                    return MarketUnscrambleFragment.newInstance("maintenance", MarketUnscrambleDetailsActivity.class);
            }
        }

        @Override
        public int getCount() {
            return tabTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
