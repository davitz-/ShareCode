package com.example.test;

import com.example.util.Constant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * android端的代码应该没有问题，但是估计JS代码有问题，始终修改不对 
 *
 */
public class LocationActivity extends BaseWebActivity {

    @Override
    public void init() {
        mWebView.getSettings().setGeolocationEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setGeolocationDatabasePath(getFilesDir().getPath());
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.LOCATION);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);  
            super.onGeolocationPermissionsShowPrompt(origin, callback);  
        }
        
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

    }
}
