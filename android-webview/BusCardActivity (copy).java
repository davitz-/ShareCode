package com.meizu.flyme.wallet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.meizu.flyme.wallet.MzWalletActivity;
import com.meizu.flyme.wallet.R;
import com.meizu.flyme.wallet.adapter.BusCardAdapter;
import com.meizu.flyme.wallet.entry.BusCardEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daibo on 15-5-12.
 */
public class BusCardActivity extends MzWalletActivity {

    private Toolbar mToolbar;

    private RecyclerView mCardList;
    private BusCardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_recycleview);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle(getResources().getString(R.string.city_bus_card));
        setSupportActionBar(mToolbar);

        mCardList = (RecyclerView) this.findViewById(R.id.recyclerView);
        mCardList.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new BusCardAdapter(this);
        mCardList.setAdapter(mCardAdapter);

        showBusCardView(getCardsData());
    }


    public void showBusCardView(List<BusCardEntry> cards) {
        mCardAdapter.setCards(cards);
        mCardAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bus_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_add_bus_card:
                break;
            default:
                break;
        }
        return true;
    }

    private List<BusCardEntry> getCardsData() {
        List<BusCardEntry> cards = new ArrayList<>();

        for (int a = 0; a < 2; a++) {
            BusCardEntry entry = new BusCardEntry();
            entry.cardNo = "0076 5523 332";
            entry.balance = "33";
            entry.cardName = "岭南通";
            cards.add(entry);
        }
        return cards;
    }
}
