package com.example.test;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.Toast;

import com.example.util.Constant;

public class LongPressActivity extends BaseWebActivity{

    @Override
    public void init() {
        mWebView.setOnCreateContextMenuListener(menuListener);
        mWebView.loadUrl(Constant.WEB_ADDRESS + Constant.LONG_PRESS);
    }
    

    private  OnCreateContextMenuListener menuListener = new OnCreateContextMenuListener() {
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            HitTestResult result = ((WebView) v).getHitTestResult();
            if (null == result)
                return;
            //获取当前被长按位置的类型，包括图片、邮箱、电话、地图坐标、日期、输入框等...
            String tipType = result.getType() + "";
            
            //获取长按图片地址、邮箱等详细信息
            String info = result.getExtra();
            
            //获取到相关信息可以自定义拨号、下载等操作
            Toast.makeText(mContext, tipType + "-" + info, Toast.LENGTH_SHORT).show();
        }
    };
}
