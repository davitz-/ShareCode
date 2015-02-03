package com.example.test;

import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VedioActivity extends BaseWebActivity {

	private final static String CUR_URL = "http://v.baidu.com/square/";

	@Override
	public void init() {
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.getSettings().setPluginState(PluginState.ON);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.setVisibility(View.VISIBLE);
		mWebView.loadUrl(CUR_URL);
	}

	// 不设置该内部类点击链接会跳转到浏览器
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
