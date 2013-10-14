package com.edwardsark.testforreksoft;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ThreeActivity extends Activity {
	ViewSwitcher switcher;
	ImageView imgView1;
	ImageView imgView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three);
		imgView1 = (ImageView)findViewById(R.id.imageView1);
		imgView2 = (ImageView)findViewById(R.id.imageView2);
		
		
		Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.image_color);
		imgView2.setImageBitmap(toGrayscale(tempBMP));
		imgView1.setImageBitmap(tempBMP);
		Timer myTimer = new Timer();
		
			MyTimerTask myTimerTask= new MyTimerTask();
			myTimer.scheduleAtFixedRate(myTimerTask, 0, 4000);
		switcher = (ViewSwitcher)findViewById(R.id.switcher);
		
		
	}
	public Bitmap toGrayscale(Bitmap bmpOriginal)
    {        
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();    

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
	protected Drawable convertToGrayscale(Drawable drawable)
	{
	    ColorMatrix matrix = new ColorMatrix();
	    matrix.setSaturation(0);

	    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

	    drawable.setColorFilter(filter);

	    return drawable;
	}
	private class MyTimerTask extends TimerTask {
	    @Override
	    public void run() {
	        runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	            	
	               //code to get and send location information
	            	if (switcher.getDisplayedChild() == 0) {
		                switcher.showNext();
		            } else {
		                switcher.showPrevious();
		            }
	            }
	        });
	    }
	}
}
