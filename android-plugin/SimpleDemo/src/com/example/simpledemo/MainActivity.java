package com.example.simpledemo;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartPluginClicked(View v) {
        Intent intent = new Intent("com.example.simpleplugin.PluginMainActivity");
        startActivity(intent);
    }
    

    /**
     * 安装apk的方法
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 卸载apk的方法
     */
    public void deleteApk(String pkgName) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + pkgName));
        startActivity(intent);
    }
}
