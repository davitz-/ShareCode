package com.example.test;

import com.example.util.Constant;
import com.example.util.MutualWithJs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * native android的方法和JS方法交互 JS 中获取android程序中的数据
 * 
 * @author daibo
 * 
 */
public class MutualActivity extends BaseWebActivity {

	private Button btnMutual = null;

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void init() {
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.setWebViewClient(new MyCustomWebViewClient());
		mWebView.addJavascriptInterface(new MutualWithJs(this), "Android");
		mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.MUTUAL);

		mViewStub.setLayoutResource(R.layout.common_btn);
		mViewStub.inflate();
		btnMutual = (Button) this.findViewById(R.id.button1);
		btnMutual.setText("Android调用JS中的方法");
		btnMutual.setOnClickListener(clickListener);
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

	// 不设置该内部类点击链接会跳转到浏览器
	private class MyCustomWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	private android.view.View.OnClickListener clickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// android 调用JS中的方法 javascript:+ js方法名()
				mWebView.loadUrl("javascript:showJSDialog()");
				break;
			default:
				break;
			}
		}
	};
}
