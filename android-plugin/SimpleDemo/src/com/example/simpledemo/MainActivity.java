package com.example.simpledemo;

import android.app.Activity;
import android.content.Intent;
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
}
