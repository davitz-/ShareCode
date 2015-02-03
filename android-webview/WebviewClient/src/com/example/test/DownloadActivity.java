package com.example.test;

import com.example.util.Constant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;

public class DownloadActivity extends BaseWebActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void init() {
		mWebView.setDownloadListener(new MyDownListener());
		mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.DOWNLOAD);
	}

	private class MyDownListener implements DownloadListener {

		@Override
		public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype, long contentLength) {
			//这里是打开内置浏览器进行下载，实际项目中可调用自定义的下载方法进行下载
			Uri uri = Uri.parse(url);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}
}
