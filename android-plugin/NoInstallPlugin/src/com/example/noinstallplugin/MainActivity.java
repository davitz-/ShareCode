package com.example.noinstallplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity{

    private Activity hostActivity = null;

    private LinearLayout layout = null;

    private TextView tvMatch = null;

    protected void createPluginView(Activity activity, Bundle savedInstanceState) {
        this.hostActivity = activity;
        String hostStr = savedInstanceState.getString("function");

        layout = new LinearLayout(hostActivity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        tvMatch = new TextView(hostActivity);
        tvMatch.setText(hostStr);

        Button button = new Button(hostActivity);
        button.setText("press me show toast...");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hostActivity, "button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = new Button(hostActivity);
        button2.setText("press me change all view");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostActivity.startActivity(new Intent(hostActivity, OtherActivity.class));
            }
        });
        layout.addView(tvMatch);
        layout.addView(button);
        layout.addView(button2);

        hostActivity.setContentView(layout);
    }
}
