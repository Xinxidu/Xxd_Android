package com.xinxidu.xxd.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.xinxidu.xxd.R;


public class LogoActivity extends Activity {
	private ImageView ivLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		ivLogo = (ImageView) findViewById(R.id.iv_icon);
		AlphaAnimation  aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(2000);
		ivLogo.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {
			}
			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				Intent intent =new Intent();
				intent.setClass(LogoActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
