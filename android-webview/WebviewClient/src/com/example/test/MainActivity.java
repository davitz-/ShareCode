package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private static final SampleConfig[] samplesConfig = new SampleConfig[] {
			new SampleConfig("MutualActivity", MutualActivity.class),
			new SampleConfig("ErrorUrlActivity", ErrorUrlActivity.class),
			new SampleConfig("SettingActivity", SettingActivity.class), 
			new SampleConfig("ChromeClientActivity", ChromeClientActivity.class),
			new SampleConfig("DownloadActivity", DownloadActivity.class),
			new SampleConfig("DensityDiffActivity", DensityDiffActivity.class),
            new SampleConfig("VedioActivity", VedioActivity.class),
            new SampleConfig("CacheActivity", AppCacheActivity.class),
            new SampleConfig("LongPressActivity", LongPressActivity.class),
            new SampleConfig("LocationActivity", LocationActivity.class),
            new SampleConfig("LocalStorageActivity", LocalStorageActivity.class),
            new SampleConfig("SessionStorageActivity", SessionStorageActivity.class)};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getTitleList()));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
        if (position >= 0 && position < samplesConfig.length)
            startActivity(new Intent(this, samplesConfig[position].targetClass));
	}

	private List<String> getTitleList() {
		List<String> titles = new ArrayList<String>();
		for (SampleConfig config : samplesConfig) {
			titles.add(config.title);
		}
		return titles;
	}

	private static class SampleConfig {
		public final String title;
		public final Class<?> targetClass;

		SampleConfig(String title, Class<?> targetClass) {
			this.title = title;
			this.targetClass = targetClass;
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			this.finish();
			return true;
		}
		return false;
	}
}
