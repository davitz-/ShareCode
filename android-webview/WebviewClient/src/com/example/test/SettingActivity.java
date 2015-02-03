package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class SettingActivity extends Activity {

	private final static String[] CUR_URL = new String[] {
			"http://www.baidu.com/", "http://oa.meizu.com/seeyon/index.jsp" };

	private int URL_INDEX = 0;

	private WebView mWebView = null;

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		mWebView = (WebView) this.findViewById(R.id.activity_webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setBuiltInZoomControls(false);
		mWebView.loadUrl(CUR_URL[URL_INDEX]);
	}

	public void onChangeUrlClicked(View v) {
		URL_INDEX = URL_INDEX == CUR_URL.length - 1 ? 0 : ++URL_INDEX;
		mWebView.loadUrl(CUR_URL[URL_INDEX]);
	}

	
	public void onEnableZoomClicked(View v) {
		mWebView.getSettings().setBuiltInZoomControls(true);
	}

	private int styleIndex = 0;
	@SuppressLint("InlinedApi")
	public void onScrollBarClicked(View v) {
		styleIndex++;
		int[] styleId = new int[]{
				//前三个需要API11
				WebView.SCROLLBAR_POSITION_DEFAULT,
				WebView.SCROLLBAR_POSITION_LEFT,
				WebView.SCROLLBAR_POSITION_RIGHT, 
				WebView.SCROLLBARS_INSIDE_INSET,
				WebView.SCROLLBARS_INSIDE_OVERLAY,
				WebView.SCROLLBARS_OUTSIDE_INSET, 
				WebView.SCROLLBARS_OUTSIDE_OVERLAY
				};
		String[] styleStr = new String[]{
				"SCROLLBAR_POSITION_DEFAULT",
				"SCROLLBAR_POSITION_LEFT",
				"SCROLLBAR_POSITION_RIGHT",
				"SCROLLBARS_INSIDE_INSET",
				"SCROLLBARS_INSIDE_OVERLAY",
				"SCROLLBARS_OUTSIDE_INSET",
				"SCROLLBARS_OUTSIDE_OVERLAY"
				};
		mWebView.setScrollBarStyle(styleId[styleIndex]);
		Toast.makeText(this, styleStr[styleIndex], Toast.LENGTH_LONG).show();
	}

}
