package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.utils.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by limingquan on 2016/9/8.
 * 华通登录
 */
public class HuaTongLoginActivity extends Activity {

    public static void startHuaTongLoginActivity(Context context) {
        Intent intent = new Intent(context, HuaTongLoginActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.strut)
    View strut;
    @BindView(R.id.shipan)
    TextView shipan;
    @BindView(R.id.monipan)
    TextView monipan;
    @BindView(R.id.view_sp)
    View viewSp;
    @BindView(R.id.view_mnp)
    View viewMnp;
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
    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_pass)
    EditText etLoginPass;
    @BindView(R.id.tv_for_verification_code)
    ImageView tvForVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.ll_login_body)
    LinearLayout llLoginBody;
    @BindView(R.id.cb_mima)
    CheckBox cbMima;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.tv_login_commit)
    TextView tvLoginCommit;
    @BindView(R.id.tv_open_account)
    TextView tvOpenAccount;
    private SharedPreferences sp;
    private String etLoginUserValue;
    private String etLoginPassValue;
    //图片验证
    private CodeUtils codeUtils;
    private String codeStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hua_tong_lagin_activity);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        viewSp.setVisibility(View.VISIBLE);
        shipan.setTextColor(getResources().getColor(R.color.shortlease_main_text_blue));
        shipan.setBackgroundColor(getResources().getColor(R.color.hui_color));
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        createConfirmCode();
        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            cbMima.setChecked(true);
            etLoginUser.setText(sp.getString("USER_NAME", ""));
            etLoginPass.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                //设置默认是自动登录状态
                cbAuto.setChecked(true);
                //跳转界面
                Intent intent = new Intent(HuaTongLoginActivity.this, HotActivity.class);
                startActivity(intent);

            }
        }
    }

    @OnClick(R.id.shipan)
    public void shipan(View view) {
        viewSp.setVisibility(View.VISIBLE);
        viewMnp.setVisibility(View.GONE);
        shipan.setTextColor(getResources().getColor(R.color.shortlease_main_text_blue));
        shipan.setBackgroundColor(getResources().getColor(R.color.hui_color));
        monipan.setBackgroundColor(getResources().getColor(R.color.white));
        monipan.setTextColor(getResources().getColor(R.color.black));

    }

    @OnClick(R.id.monipan)
    public void monipan(View view) {
        viewMnp.setVisibility(View.VISIBLE);
        viewSp.setVisibility(View.GONE);
        monipan.setTextColor(getResources().getColor(R.color.shortlease_main_text_blue));
        monipan.setBackgroundColor(getResources().getColor(R.color.hui_color));
        shipan.setTextColor(getResources().getColor(R.color.black));
        shipan.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick({R.id.back, R.id.tv_for_verification_code, R.id.tv_login_commit, R.id.tv_open_account, R.id.cb_mima, R.id.cb_auto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_for_verification_code:
                createConfirmCode();
                break;
            case R.id.tv_login_commit:
                login();
                yanzhengma();
                break;
            case R.id.tv_open_account:
//                String url = "http://trade.huatongsilver.com/accountweb/web/reg2/reg.html";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
                HuaTongRegisterActivity.startHuaTongRegisterActivity(this);
                break;
            case R.id.cb_mima:
                if (cbMima.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
            case R.id.cb_auto:
                if (cbAuto.isChecked()) {
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
                break;
        }
    }

    private void yanzhengma() {
        codeStr = etVerificationCode.getText().toString().trim();
        if (null == codeStr || TextUtils.isEmpty(codeStr)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        String code = codeUtils.getCode();
        Log.e("code", code);
        if (code.equalsIgnoreCase(codeStr)) {
            Toast.makeText(this, "验证码正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void createConfirmCode() {
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        tvForVerificationCode.setImageBitmap(bitmap);
    }

    private void login() {
        etLoginUserValue = etLoginUser.getText().toString();
        etLoginPassValue = etLoginPass.getText().toString();

        if (etLoginUserValue.equals("xxd") && etLoginPassValue.equals("123")) {

            //登录成功和记住密码框为选中状态才保存用户信息
            if (cbMima.isChecked()) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", etLoginUserValue);
                editor.putString("PASSWORD", etLoginPassValue);
                editor.commit();
            }

            //跳转界面
            Intent intent = new Intent(HuaTongLoginActivity.this, HotActivity.class);
            startActivity(intent);
            Toast.makeText(HuaTongLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(HuaTongLoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
        }
    }
}
