package com.example.test;

import com.example.util.Constant;

import android.webkit.WebChromeClient;

public class SessionStorageActivity extends BaseWebActivity {

    @Override
    public void init() {
        String dbPath = this.getApplicationContext().getDir("database", MODE_PRIVATE).getPath();
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDatabasePath(dbPath);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(ChromeClient);
        mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.SESSION_STORAGE);
    }

    private WebChromeClient ChromeClient = new WebChromeClient() {
        public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota,
                long estimatedDatabaseSize, long totalQuota, android.webkit.WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(estimatedDatabaseSize * 2);
        };

    };
}
