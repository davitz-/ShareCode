package com.example.test;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.util.Constant;

/**
 * 处理不同分辨率显示的问题 提供你的页面的目标分辨率来控制缩放，并通过使用CSS或者Javascript来为不同分辨率提供不同图像。
 * 
 * 官方文档： http://developer.android.com/guide/webapps/targeting.html
 * 
 * @author daibo
 */
public class DensityDiffActivity extends BaseWebActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void init() {

		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.loadUrl(Constant.WEB_ADDRESS+Constant.DENSITY);
	}

	public final class MyWebChromeClient extends WebChromeClient {
		@Override
		// android 显示JS存在问题，因此捕获对话框内容并重新生成对话框
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			new AlertDialog.Builder(mContext).setTitle("javascript...")
					.setMessage(message)
					.setPositiveButton("确定", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					}).create().show();
			return true;
		}

		@Override
		public void onProgressChanged(WebView view, int progress) {
			super.onProgressChanged(view, progress);
		}
	}

}
