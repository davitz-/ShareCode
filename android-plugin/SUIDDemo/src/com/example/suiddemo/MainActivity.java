package com.example.suiddemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartPluginClicked(View v) throws Exception {
        PackageManager pkgManager = this.getPackageManager();
        String pkgName = this.getPackageName();
        String sharedUID = "";
        try {
            // 获取当前app的suid
            sharedUID = pkgManager.getPackageInfo(pkgName, PackageManager.GET_UNINSTALLED_PACKAGES).sharedUserId;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        List<PackageInfo> allPi = pkgManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<PackageInfo> pluginPi = new ArrayList<PackageInfo>();

        for (PackageInfo pkg : allPi) {
            // 查找具有相同suid的包，并且排除自身
            if (!sharedUID.equals(pkg.sharedUserId) || pkgName.equals(pkg.packageName))
                continue;
            pluginPi.add(pkg);
        }

        //当前测试代码默认启动其中一个插件
        Context targetContext = this.createPackageContext(pluginPi.get(0).packageName, Context.CONTEXT_INCLUDE_CODE
                | Context.CONTEXT_IGNORE_SECURITY);
        // 根据context读取插件下的文件数据
        InputStream pluginInput = targetContext.getAssets().open("plugin.txt");
        String action = getStrFromInputSteam(pluginInput);
        Intent intent = new Intent(action);
        startActivity(intent);
    }

    public String getStrFromInputSteam(InputStream in) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        // 最好在将字节流转换为字符流的时候 进行转码
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
