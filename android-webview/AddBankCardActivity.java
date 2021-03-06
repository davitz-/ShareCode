package com.meizu.flyme.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.meizu.flyme.wallet.MzWalletActivity;
import com.meizu.flyme.wallet.R;

/**
 * Created by daibo on 15-5-12.
 */
public class AddBankCardActivity extends MzWalletActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private EditText mEtCardNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle(getResources().getString(R.string.add_bank_card));
        setSupportActionBar(mToolbar);

        mEtCardNo = (EditText) this.findViewById(R.id.card_no_edit);
        findViewById(R.id.next_step).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                Intent intent = new Intent(this, ConfrimCardInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
