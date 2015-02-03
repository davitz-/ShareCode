package com.example.test;

import java.io.File;

import android.util.Log;
import android.webkit.WebChromeClient;

import com.example.util.Constant;

public class LocalStorageActivity extends BaseWebActivity {
    
    private String DB_PATH = null;
    @Override
    public void init() {
        DB_PATH = this.getApplicationContext().getDir("database", MODE_PRIVATE).getPath();
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDatabasePath(DB_PATH);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(ChromeClient);
        mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.LOCAL_STORAGE);
    }

    private WebChromeClient ChromeClient = new WebChromeClient() {
        public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota,
                long estimatedDatabaseSize, long totalQuota, android.webkit.WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(estimatedDatabaseSize * 2);
        };

    };

    protected void onPause() {
        clearWebViewCache();
    };
    
    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache() {

        // 清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // WebView 缓存文件
        File appCacheDir = new File(DB_PATH);
        Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
        Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        // 删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        // 删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     * 
     * @param file
     */
    public void deleteFile(File file) {

        Log.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }
}
