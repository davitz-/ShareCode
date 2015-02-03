package com.example.dexdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @SuppressLint("NewApi")
    public void onStartDexPluginClicked(View v){
        String dexpath = "/mnt/sdcard/dexplugin.jar";
        String dexoutputpath =  getDir("dex", 0).getAbsolutePath();
        
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath, dexoutputpath, null, localClassLoader);
        try {
                //这里无法从jar包中获取到启动类，因此需要指定类名
                Class<?> localClass = localDexClassLoader.loadClass("com.example.dexplugin1.MainActivity");
                Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
                Object instance = localConstructor.newInstance(new Object[] {});

                Bundle paramBundle = new Bundle();
                paramBundle.putString("function", "this msg was passed by main app");
                Method methodonCreate = localClass.getDeclaredMethod("createPluginView", new Class[] { Activity.class, Bundle.class });
                methodonCreate.setAccessible(true);
                methodonCreate.invoke(instance, new Object[] { MainActivity.this,  paramBundle });
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
}
