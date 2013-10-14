package com.edwardsark.testforreksoft;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class OneActivity extends Activity implements OnClickListener {
	WebView webView;
	ProgressDialog _dialog ; 
	String urlWeb;
	Button refresh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		urlWeb = "http://www.yandex.ru";
		webView = (WebView)findViewById(R.id.webView1);
		refresh = (Button)findViewById(R.id.buttonRefresh);
		refresh.setOnClickListener(this);
		webView.getSettings().setJavaScriptEnabled(true);
		
		
		
		
		webView.setWebViewClient(new WebViewClient(){
			
			@Override
		       public void onPageStarted(WebView view, String url, Bitmap favicon) {
		        // TODO Auto-generated method stub
		        _dialog =ProgressDialog.show(OneActivity.this, "", "Загрузка страницы...");
		        super.onPageStarted(view, url, favicon);
		       }
		       @Override
		       public void onPageFinished(WebView view, String url) {
		        // TODO Auto-generated method stub
		        super.onPageFinished(view, url);
		        _dialog.dismiss();
		       }

		       @Override
		       public void onReceivedError(WebView view, int errorCode,
		         String description, String failingUrl) {
		        // TODO Auto-generated method stub
		        super.onReceivedError(view, errorCode, description, failingUrl);
		        try{
		         _dialog.dismiss();
		        }catch (Exception e) {
		         // TODO: handle exception
		        }
		       }
			
			
		});
		if(isOnline()){
			refresh.setVisibility(View.GONE);
			webView.loadUrl(urlWeb);
			}else{
				Toast.makeText(this, R.string.network_unavailable,
						   Toast.LENGTH_LONG).show();
			}
		
	}
	public boolean wasOffline;
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			wasOffline = false;
			return true;
		}
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonRefresh:
			if(isOnline()){
				refresh.setVisibility(View.GONE);
				webView.loadUrl(urlWeb);
				}else{
					Toast.makeText(this, R.string.network_unavailable,
							   Toast.LENGTH_LONG).show();
				}
			
		
		}
	}
}
