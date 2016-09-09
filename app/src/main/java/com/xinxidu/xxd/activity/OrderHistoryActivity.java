package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.utils.TimeDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/9.
 */
public class OrderHistoryActivity extends Activity {

    public static void startOrderHistoryActivity(Context context) {
        Intent intent = new Intent(context, OrderHistoryActivity.class);
        context.startActivity(intent);
    }
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
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.ll_decide)
    LinearLayout llDecide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history_activity);
        ButterKnife.bind(this);
        tvTitle.setText("历史订立单");
    }

    @OnClick({R.id.back, R.id.tv_start, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_start:
                getTime(tvStart);
                break;
            case R.id.tv_end:
                getTime(tvEnd);
                break;
        }
    }

    //开始和结束时间
    private void getTime(final TextView time) {
        final TimeDialogUtils dialog = TimeDialogUtils.getInstance();
        dialog.dateDialog(this, true);
        dialog.setOnDateClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                time.setText(dialog.getCurrDate());
                dialog.dismiss();
            }
        });
    }
}
