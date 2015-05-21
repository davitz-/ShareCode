package com.meizu.flyme.wallet.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meizu.flyme.wallet.MzWalletActivity;
import com.meizu.flyme.wallet.R;

/**
 * Created by daibo on 15-5-12.
 */
public class BusPayActivity extends MzWalletActivity implements View.OnClickListener {

    private static int ANIMATE_TIME;

    private Toolbar mToolbar;
    private LinearLayout mLltShadow;
    private LinearLayout mLltDialog;
    private TextView mTvPocketMoney;
    private TextView mTvUseSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_pay);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle(getResources().getString(R.string.city_bus_card));
        setSupportActionBar(mToolbar);

        ANIMATE_TIME = getResources().getInteger(R.integer.dialog_animate);

        mLltShadow = (LinearLayout) this.findViewById(R.id.shadow_layout);
        mLltDialog = (LinearLayout) this.findViewById(R.id.dialog_layout);
        mTvPocketMoney = (TextView) this.findViewById(R.id.pocket_money);
        mTvUseSpec = (TextView) this.findViewById(R.id.user_specification);


        findViewById(R.id.trade_record).setOnClickListener(this);
        findViewById(R.id.set_default_card).setOnClickListener(this);
        findViewById(R.id.delete_card).setOnClickListener(this);
        findViewById(R.id.shadow_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trade_record:
                startActivity(new Intent(this, ConsumeRecordActivity.class));
                break;
            case R.id.set_default_card:
                dismissShadowView();
                //TODO
                break;
            case R.id.delete_card:
                dismissShadowView();
                //TODO
                break;
            case R.id.shadow_layout:
                dismissShadowView();
                break;
            default:
                break;
        }
    }

    private void showShadowView() {
        mLltShadow.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(mLltShadow, "alpha", 0, 1).setDuration(ANIMATE_TIME).start();
        ObjectAnimator.ofFloat(mLltDialog, "translationY", -mLltDialog.getHeight(), 0)
                .setDuration(ANIMATE_TIME).start();
    }

    private void dismissShadowView() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mLltShadow, "alpha", 1, 0);
        ObjectAnimator.ofFloat(mLltDialog, "translationY", 0, -mLltDialog.getHeight())
                .setDuration(ANIMATE_TIME).start();

        oa.setDuration(ANIMATE_TIME);
        oa.start();
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLltShadow.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quick_pay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_setting:
                if (mLltShadow.getVisibility() == View.GONE) {
                    showShadowView();
                }
                break;
            default:
                break;
        }
        return true;
    }
}
