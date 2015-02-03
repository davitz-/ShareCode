package com.example.dexplugin1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class OtherActivity extends Activity{

    private TextView tvMatch = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvMatch = new TextView(this);
        tvMatch.setText("this is plugin's second view");
        tvMatch.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setContentView(tvMatch);
    }
    
}
