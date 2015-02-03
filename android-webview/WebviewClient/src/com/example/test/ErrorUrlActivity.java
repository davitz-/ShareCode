package com.example.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * 传递给webview的URl错误时的处理方式
 * 
 * @author daibo
 *
 */
public class ErrorUrlActivity extends BaseWebActivity{

	private String CUR_URL = "http://127.0.0.1/";

	private TextView tvLog = null;
	
	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void init() {
		mWebView.setWebViewClient(new MyWebViewClient());
		mViewStub.setLayoutResource(R.layout.common_txt);
		mWebView.loadUrl(CUR_URL);
		
		mViewStub.inflate();
		tvLog = (TextView) this.findViewById(R.id.textView1);
	}
	
	private class MyWebViewClient extends WebViewClient{
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			tvLog.setText("errorCode: " + errorCode + "\n description: " + description +"\n failingUrl: " + failingUrl);
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}
}
