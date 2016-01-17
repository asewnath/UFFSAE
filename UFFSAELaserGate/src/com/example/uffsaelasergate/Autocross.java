package com.example.uffsaelasergate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import android.annotation.SuppressLint;

//import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
//import android.content.Intent;
//import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Autocross extends Activity {

	private Button startButton;
	private Button stopButton;
	private Button saveButton;
	private TextView timerValue;

	private long startTime = 0L;
	private TextView[] recordedTime = new TextView [5];
	private long[] times = new long [50];
	private Handler customHandler = new Handler();
	int click = 0;
	
	//be a good coder and change to private
	String baseFolder;
	//Context context;
	Boolean bool;
	String on;
	String time;
	String s;
	String final_data;
	String majString;
	String minString;
	String filename;
	
	int secs;
	int mins;
	int milliseconds;
	
	long timeInMilliseconds = 0L;
	long timeMillis = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	long elapsedTime = 0L;
	long elapsedTime1 = 0L;
	char chars[] = new char[1000];
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocross);
		
		timerValue = (TextView)findViewById(R.id.textView2);
		//elapsed1
		recordedTime[0]= (TextView)findViewById(R.id.elapsed1);
		//elapsed2
		recordedTime[1]= (TextView)findViewById(R.id.elapsed2);
		//elapsed3
		recordedTime[2]= (TextView)findViewById(R.id.elapsed3);
		//elapsed4 
		recordedTime[3]= (TextView)findViewById(R.id.elapsed4);
		//elapsed5 
		recordedTime[4]= (TextView)findViewById(R.id.elapsed5);
		
		
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
				times[click] = timeMillis;	
				
				if(click == 0){
					times[click] = timeMillis;
					int secs = (int) (times[click]/1000);
					int mins = secs/60;
					secs = secs % 60;
					int milliseconds = (int) (times[click] % 1000);
					
					recordedTime[click].setText("" + String.format("%d. ", click + 1)+ String.format("%02d", mins) + ":"
							+ String.format("%02d", secs) + ":" 
							+ String.format("%03d", milliseconds));
					
					elapsedTime = times[click];	
				}
							
				if(click > 0 && click < 5){
					
					times[click] = timeMillis - elapsedTime; 
					int calc = click;
					int count = click + 1;
					
					for(int i = 0; i <= click ; i++){
					int secs = (int) (times[calc]/1000);
					int mins = secs/60;
					secs = secs % 60;
					int milliseconds = (int) (times[calc] % 1000);

					recordedTime[i].setText("" + String.format("%d. ", count)+ String.format("%02d", mins) + ":"
							+ String.format("%02d", secs) + ":" 
							+ String.format("%03d", milliseconds));
					calc--;
					count--;
					}
					
					elapsedTime = elapsedTime + times[click];
				}

					if(click >= 5){
					times[click] = timeMillis - elapsedTime;
					int count = click + 1;
					int calc = click;
					
					for(int i = 0; i < 5; i++){
						int secs = (int) (times[calc]/1000);
						int mins = secs/60;
						secs = secs % 60;
						int milliseconds = (int) (times[calc] % 1000);
						recordedTime[i].setText("" + String.format("%d. ", count)+ String.format("%02d", mins) + ":"
								+ String.format("%02d", secs) + ":" 
								+ String.format("%03d", milliseconds));
						calc--;
						count--;
					}	
					elapsedTime = elapsedTime + times[click];
				}
					
				click++;
			}
		});
		
		saveButton = (Button)findViewById(R.id.button3);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			//Current issue: not receiving the data properly
			
			public void onClick(View v) {

				secs = 0;
				mins = 0;
				milliseconds = 0;
				
				majString = "\n";
				filename = "Test.txt";
				
				Context context = getApplicationContext();
				CharSequence text = "Times Saved";
				
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){	
					baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
				}
				else{
					baseFolder = context.getFilesDir().getAbsolutePath();
				}
				
				//Format the long from times[]
				for(int i = 0; i < click; i++){
					secs = (int) (times[i] / 1000);
					mins = secs/60;
					secs = secs % 60;
					milliseconds = (int)(times[i] % 1000);
					
					minString = "" + String.format("%d. ", i + 1) +
								String.format("%02d", mins) + ":" +
								String.format("%02d", secs) + ":" +
								String.format("%03d", milliseconds);
					//Combine the strings
					majString = majString + minString + "\n";
				}
				
				String mFile = baseFolder + filename;
				
				//Creating file to write into
				File file = new File(mFile);
								try {
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(majString.getBytes());
					fos.close();	
					//toast.show();
					
					
					
					
					//Start new intent to pass data to Archive_AutoX
					Intent intent = new Intent(Autocross.this, Archive_Autox.class);
					intent.putExtra("key", mFile);
					startActivity(intent); //takes you straight to autox archive
					
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		getMenuInflater().inflate(R.menu.autocross, menu);
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
