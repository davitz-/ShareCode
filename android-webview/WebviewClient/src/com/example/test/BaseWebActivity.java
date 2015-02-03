package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewStub;
import android.webkit.WebView;

@SuppressLint("JavascriptInterface")
public abstract class BaseWebActivity extends Activity {

	public final static String TAG = "TAG";
	
	public WebView mWebView = null;

	public ViewStub mViewStub = null;
	
	public Context mContext = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.web_activity);
		initBase();
		init();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initBase(){
		mContext = this;
		mWebView = (WebView) this.findViewById(R.id.base_webview);
		mViewStub = (ViewStub) this.findViewById(R.id.viewStub);
		mWebView.getSettings().setJavaScriptEnabled(true);
	}
	
	/**
	 * 设置url的值  设置webview属性 初始化mViewStub
	 * 
	 */
	public abstract void init();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			//如果还有其的dialog需要处理返回逻辑也写在这里
			mWebView.goBack(); 
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
