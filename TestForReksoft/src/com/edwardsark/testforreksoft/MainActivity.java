package com.edwardsark.testforreksoft;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 TabHost tabHost = getTabHost();
	        
	        TabHost.TabSpec tabSpec;
	        
	        tabSpec = tabHost.newTabSpec("tag1");
	        tabSpec.setIndicator("Yandex");
	        tabSpec.setContent(new Intent(this, OneActivity.class));
	        tabHost.addTab(tabSpec);
	        
	        tabSpec = tabHost.newTabSpec("tag2");
	        tabSpec.setIndicator("Map");
	        tabSpec.setContent(new Intent(this, TwoActivity.class));
	        tabHost.addTab(tabSpec);
	        
	        tabSpec = tabHost.newTabSpec("tag3");
	        tabSpec.setIndicator("Image");
	        tabSpec.setContent(new Intent(this, ThreeActivity.class));
	        tabHost.addTab(tabSpec);

	}

	

}
