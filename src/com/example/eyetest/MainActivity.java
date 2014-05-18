package com.example.eyetest;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
		
    public final static String CORRECT = "com.example.myfirstapp.CORRECT";
    public final static String INCORRECT = "com.example.myfirstapp.INCORRECT";

	
	// local state variables
	ImageView customImageView;
	double chartCounter;
	Random rand = new Random();
	private int failCount;
	private int passCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize variables
		customImageView = (ImageView) findViewById(R.id.customImageView);
		chartCounter = 1;
		failCount = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
	    int action = event.getAction();
	    int keyCode = event.getKeyCode();
	        switch (keyCode) {
	        case KeyEvent.KEYCODE_VOLUME_UP:
	            if (action == KeyEvent.ACTION_UP) {
	                changeChart();
	                passCount = passCount + 1;
	            }
	            return true;
	        case KeyEvent.KEYCODE_VOLUME_DOWN:
	            if (action == KeyEvent.ACTION_DOWN) {
	                if(++failCount > 3)
	                	reportSight(); 
	                changeChart();
	            }
	            return true;
	        default:
	            return super.dispatchKeyEvent(event);
	        }
	    }
	
	private void changeChart(){
		chartCounter++;
		int randSelect = rand.nextInt(4) + 1;
		int resID;
		customImageView.setScaleX((float)(1/chartCounter));
		customImageView.setScaleY((float)(1/chartCounter));
		

		
		switch(randSelect) {
		case 1:
		    customImageView.setImageResource(R.drawable.down);
			break;
		case 2:
		    customImageView.setImageResource(R.drawable.left);
			break;
		case 3:
		    customImageView.setImageResource(R.drawable.right);
			break;
		case 4:
		    customImageView.setImageResource(R.drawable.up);
			break;
		}
		customImageView.invalidate();
	}

	private void reportSight() {
		Intent sightIntent = new Intent(this, SightReportActivity.class);
	    sightIntent.putExtra(CORRECT, passCount);
	    sightIntent.putExtra(INCORRECT, failCount);
		startActivity(sightIntent);
	}
	
}
