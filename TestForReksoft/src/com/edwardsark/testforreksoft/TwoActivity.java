package com.edwardsark.testforreksoft;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TwoActivity extends Activity  {
	MapController mMapController;
    LinearLayout mView;
    LocationManager locationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		  final MapView mapView = (MapView) findViewById(R.id.map);
	        mapView.showBuiltInScreenButtons(true);

	        mapView.getMapController().getOverlayManager().addOverlay(new OverlayGeoCode(mapView.getMapController()));

	        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

	        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
	            buildAlertMessageNoGps();}
	        }

	      private void buildAlertMessageNoGps() {
	        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage("Для более точного определения местоположения необходимо включить GPS. Включить?")
	               .setCancelable(false)
	               .setPositiveButton("Да", new DialogInterface.OnClickListener() {
	                   public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                       startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
	                   }
	               })
	               .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
	                   public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                        dialog.cancel();
	                   }
	               });
	        final AlertDialog alert = builder.create();
	        alert.show();
	    
	        if(!isOnline()){
	        	Toast.makeText(this, R.string.network_unavailable_map,
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
	

	   

}
