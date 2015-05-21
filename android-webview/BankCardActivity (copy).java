package com.meizu.flyme.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.meizu.flyme.wallet.MzWalletActivity;
import com.meizu.flyme.wallet.R;
import com.meizu.flyme.wallet.adapter.BankCardAdapter;
import com.meizu.flyme.wallet.entry.BankCardEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daibo on 15-5-11.
 */
public class BankCardActivity extends MzWalletActivity {

    private Toolbar mToolbar;

    private RecyclerView mCardList;
    private BankCardAdapter mCardAdapter;
ddddd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_recycleview);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle(getResources().getString(R.string.quick_pay_card));
        setSupportActionBar(mToolbar);

        mCardList = (RecyclerView) this.findViewById(R.id.recyclerView);
        mCardList.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new BankCardAdapter(this);
        mCardList.setAdapter(mCardAdapter);

        showBankCardListView(getBankCardInfo());
    }

    public void showBankCardListView(List<BankCardEntry> set) {
        mCardAdapter.setCards(set);
        mCardAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bank_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_add_bank_card:
                Intent intent = new Intent(this, AddBankCardActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    private List<BankCardEntry> getBankCardInfo() {
        List<BankCardEntry> cards = new ArrayList<>();
        for (int a = 0; a < 1; a++) {
            BankCardEntry entry = new BankCardEntry();
            entry.bankName = "招商银行";
            entry.cardNo = "**** **** **** 3245";
            entry.pocketMoney = "300.00元";
            entry.type = "信用卡";
            entry.isDefaultCard = true;
            cards.add(entry);
        }
        for (int a = 0; a < 2; a++) {
            BankCardEntry entry = new BankCardEntry();
            entry.bankName = "工商银行";
            entry.cardNo = "**** **** **** 3245";
            entry.pocketMoney = "300.00元";
            entry.type = "储蓄卡";
            cards.add(entry);
        }
        return cards;
    }

}
