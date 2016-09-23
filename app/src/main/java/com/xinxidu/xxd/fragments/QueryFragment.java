package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/21.
 */
public class QueryFragment extends Fragment {
    @BindView(R.id.account_information)
    TextView accountInformation;
    @BindView(R.id.day_single)
    TextView daySingle;
    @BindView(R.id.single_swap)
    TextView singleSwap;
    @BindView(R.id.history_single)
    TextView historySingle;
    @BindView(R.id.history_swap)
    TextView historySwap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.query_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({ R.id.account_information, R.id.day_single, R.id.single_swap, R.id.history_single, R.id.history_swap})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_information:
                break;
            case R.id.day_single:
                break;
            case R.id.single_swap:
                break;
            case R.id.history_single:
                break;
            case R.id.history_swap:
                break;
        }
    }
}
