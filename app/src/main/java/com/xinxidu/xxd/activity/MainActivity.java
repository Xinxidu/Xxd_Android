package com.xinxidu.xxd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rogers.kit.base.BaseActivity;
import com.xinxidu.xxd.R;
import com.xinxidu.xxd.fragments.FindFragment;
import com.xinxidu.xxd.fragments.HomeFagment;
import com.xinxidu.xxd.fragments.MarketFragment;
import com.xinxidu.xxd.fragments.MyFragment;
import com.xinxidu.xxd.fragments.TradeFragment;

/**
 * Created by nick on 15/10/21.
 */
public class MainActivity extends BaseActivity {

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private FrameLayout mHomeContent;
    private RadioGroup mHomeRadioGroup;
    private RadioButton mHomeHomeRb;
    private RadioButton mHomeMarketRb;
    private RadioButton mHomeMtradeRb;
    private RadioButton mHomefindRb;
    private RadioButton mHomeMeRb;

    static final int NUM_ITEMS = 5;//一共5个fragment

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
        mHomeRadioGroup.check(R.id.radio_home);
    }

    protected void initView() {
        mHomeContent = (FrameLayout) findViewById(R.id.content_frame); //tab上方的区域
        mHomeRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);  //底部的四个tab
        mHomeHomeRb = (RadioButton) findViewById(R.id.radio_home);
        mHomeMarketRb = (RadioButton) findViewById(R.id.radio_market);
        mHomeMtradeRb = (RadioButton) findViewById(R.id.radio_trade);
        mHomefindRb = (RadioButton) findViewById(R.id.radio_find);
        mHomeMeRb = (RadioButton) findViewById(R.id.radio_me);

        //监听事件：为底部的RadioGroup绑定状态改变的监听事件
        mHomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.radio_home:
                        index = 0;
                        break;
                    case R.id.radio_market:
                        index = 1;
                        break;
                    case R.id.radio_trade:
                        index = 2;
                        break;
                    case R.id.radio_find:
                        index = 3;
                        break;
                    case R.id.radio_me:
                        index = 4;
                        break;
                    default:
                        break;
                }
                //通过fragments这个adapter还有index来替换帧布局中的内容
                Fragment fragment = (Fragment) fragments.instantiateItem(mHomeContent, index);
                //一开始将帧布局中 的内容设置为第一个
                fragments.setPrimaryItem(mHomeContent, 0, fragment);
                fragments.finishUpdate(mHomeContent);

            }
        });
    }

    //第一次启动时，我们让mHomeHomeRb这个radiobutton处于选中状态。
    // 当然了，在这之前，先要在布局文件中设置其他的某一个radiobutton（只要不是mHomeHomeRb就行）
    // 的属性为android:checked="true"，才会出发下面的这个check方法切换到mHomeHomeRb
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mHomeRadioGroup.check(R.id.radio_home);
//
//    }

    //用adapter来管理三个Fragment界面的变化。注意，我这里用的Fragment都是v4包里面的
    FragmentStatePagerAdapter fragments = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return NUM_ITEMS;//一共有5个Fragment
        }

        //进行Fragment的初始化
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0://首页
                    fragment = new HomeFagment();
                    break;
                case 1://行情
                    fragment = new MarketFragment();
                    break;
                case 2://交易
                    fragment = new TradeFragment();
                    break;
                case 3://发现
                    fragment = new FindFragment();
                    break;
                case 4://我
                    fragment = new MyFragment();
                    break;
                default:
                    break;
            }

            return fragment;
        }
    };
}
