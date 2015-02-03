package com.example.util;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MutualWithJs {
	private Context mContext;

	public MutualWithJs(Context context) {
		this.mContext = context;
	}

	@JavascriptInterface
	public void showToast(String toast) {
		Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	}

	@JavascriptInterface
	public String getAndroidTxt() {
		return "this txt come form native android.";
	}
}
