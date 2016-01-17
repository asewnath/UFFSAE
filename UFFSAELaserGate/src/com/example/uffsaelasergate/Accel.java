package com.example.uffsaelasergate;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accel extends Activity {

	private Button startButton;
	private Button stopButton;
	private TextView timerValue;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	private int click = 0;
	
	TextView recordedTime;
	long times = 0L;
	long timeInMilliseconds = 0L;
	long timeMillis = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	long elapsedTime = 0L;
	long elapsedTime1 = 0L;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accel);
		
		timerValue = (TextView)findViewById(R.id.textView2);
		//Timer
		recordedTime = (TextView)findViewById(R.id.textView3);
		//Timer
		
		startButton = (Button)findViewById(R.id.button1);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);
				
			}
		});
	
		stopButton = (Button)findViewById(R.id.button2);
		stopButton.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {

				timeMillis = SystemClock.uptimeMillis() - startTime; 
				times = timeMillis;	
				
				if(click == 0){
					times = timeMillis;
					int secs = (int) (times/1000);
					int mins = secs/60;
					secs = secs % 60;
					int milliseconds = (int) (times % 1000);
					recordedTime.setText(""+ String.format("%02d", mins) + ":"
							+ String.format("%02d", secs) + ":" 
							+ String.format("%03d", milliseconds));
					
					//elapsedTime = times[click];	
				}
							
			}
		});
		
	}
	
private Runnable updateTimerThread = new Runnable(){
		
		public void run(){
			
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			
			updatedTime = timeSwapBuff + timeInMilliseconds;
			//updatedTime = timeInMilliseconds;
			
			
			int secs = (int) (updatedTime/1000);
			int mins = secs/60;
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			timerValue.setText("" + String.format("%02d", mins) + ":"
					+ String.format("%02d", secs) + ":" 
					+ String.format("%03d", milliseconds));
			
			//timeSwapBuff += timeInMilliseconds;
			customHandler.postDelayed(this, 0);
		}
	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accel, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
