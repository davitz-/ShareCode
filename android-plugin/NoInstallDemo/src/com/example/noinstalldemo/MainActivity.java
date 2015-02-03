package com.example.noinstalldemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    private final static String TAG = "TAG";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("NewApi")
    public void onGetPluginTxtClicked(View v) throws Exception {
        Bundle paramBundle = new Bundle();
        paramBundle.putString("function", "this msg was passed by main app");
        String dexpath = "/mnt/sdcard/classes.dex";
        String dexoutputpath = getDir("dex", 0).getAbsolutePath();

        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath, dexoutputpath, null, localClassLoader);
        try {
            PackageInfo plocalObject = getPackageManager().getPackageArchiveInfo(dexpath, 1);

            if ((plocalObject.activities != null) && (plocalObject.activities.length > 0)) {
                String activityname = plocalObject.activities[0].name;
                Log.d(TAG, "activityname = " + activityname);

                Class<?> localClass = localDexClassLoader.loadClass(activityname);
                Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
                Object instance = localConstructor.newInstance(new Object[] {});
                Log.d(TAG, "instance = " + instance);

                Method methodonCreate = localClass.getDeclaredMethod("createPluginView", new Class[] { Activity.class, Bundle.class });
                methodonCreate.setAccessible(true);
                methodonCreate.invoke(instance, new Object[] { MainActivity.this,  paramBundle });
            }
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
