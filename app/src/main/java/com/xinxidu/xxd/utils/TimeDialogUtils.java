package com.xinxidu.xxd.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

import com.xinxidu.xxd.R;


/**
 * 对话框
 *
 */

public class TimeDialogUtils {
	private static Dialog log;
	private static TimeDialogUtils dialog = null;
	public static synchronized TimeDialogUtils getInstance() {
		if (dialog == null) {
			dialog = new TimeDialogUtils();
		}
		return dialog;
	}


	private TextView dialog_ok;
	private DatePicker dialog_date;
	public void dateDialog(Context context, boolean isMax) {
		log = new Dialog(context, R.style.MyDialog_Style);
		if (null == log) {
			return;
		}
		log.show();
		log.setCanceledOnTouchOutside(true);
		
		Window window = log.getWindow();
		window.setContentView(R.layout.dialog_date);
		dialog_date = (DatePicker) window.findViewById(R.id.date_picker);
		TextView dialog_no = (TextView) window.findViewById(R.id.date_no);
		dialog_ok = (TextView) window.findViewById(R.id.date_ok);
		if(isMax){
			dialog_date.setMaxDate(System.currentTimeMillis());
		}
		dialog_no.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();
			}
		});
	}
	
	public void setOnDateClickListener(OnClickListener listener){
		dialog_ok.setOnClickListener(listener);
	}
	
	public String getCurrDate() {
		if(dialog_date==null){
			return "";
		}
		return dialog_date.getYear() + "-" + (dialog_date.getMonth()+1) + "-"
				+ dialog_date.getDayOfMonth();
	}

	public void dismiss(){
		if(log!=null){
			log.dismiss();
			log=null;
		}
		System.gc();
	}
}
