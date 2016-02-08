package com.example.uffsaelasergate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		

		
		
		//*************************************************************************************************
		
		//START
		
		startButton = (Button)findViewById(R.id.button1);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);
				
			}
		});
		
		//*************************************************************************************************
		
		//STOP
		
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
		
		
		//*************************************************************************************************
		
		//SAVE
		
		saveButton = (Button)findViewById(R.id.button3);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			
		//	@SuppressWarnings("resource")
			public void onClick(View v) {
				Context context = getApplicationContext();
			//	String text = "exists";
			//	int duration = Toast.LENGTH_LONG;
			//	Toast toast = Toast.makeText(context, text, duration);
				
				//Figure out if file exists.
				//If file does not exist, create file.
				
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){	
					baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
				}
				else{
					baseFolder = context.getFilesDir().getAbsolutePath();
				}
			
			//**************************************************************************
				secs = 0;
				mins = 0;
				milliseconds = 0;
				
				majString = "\n";
				filename = "Test.txt";
				
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
				
				//***********************************************************************
				
				String mFile = baseFolder + filename;
							
				//*******************************************************************
				
		    	String g = "globalFile.txt";
		    	String globalFile = baseFolder + g;
		    	
		    	
//****************************************************************************************		    	
		    	
		    	
		    		FileInputStream gfis = null;

		    			try {

		    				
								gfis = new FileInputStream(globalFile);
								
								
				    			InputStreamReader isr = new InputStreamReader(gfis);
					    		BufferedReader br = new BufferedReader(isr);
					    		
					    		String test;
					    		int count = 0;
					    		String line;
					    		
					    		
					    		
					    		//don't need this
					    		try{
					    			while((test = br.readLine())!= null){
					    				//count++;
					    				line = br.readLine();
					    			}
					    			//int globalCount = Integer.parseInt(line);
					    		}
					    		
					    		catch(IOException e){  
					    			
					    		}
					    		
					    		/*
					    		
					    		try{
					    			gfis.getChannel().position(0);
					    		}
					    		
					    		catch(IOException e){
					    			
					    		}
					    		
					    		
					    		//don't need this
					    		String[] array = new String[count];
					    		
					    		String line;
					    		int i = 0;
					    		try{
					    			while((line = br.readLine()) != null){
					    				array[i] = line;	
					    				i++;
					    			}
					    		}
					    		catch(IOException e){
					    			
					    		}
					    		
					    		
					    		//I have an array of strings that I need to convert to
					    		//a single array. I do this by looping through my array
					    		
					    		String strTemp = "";
					    		
					    		for(int j = 0; j < array.length; j++){
					    			strTemp = strTemp + array[j];
					    			strTemp = strTemp + "\n";
					    		}

					    		//printFile.setText(strTemp);
					    		
					    		*/
					    		
					    		//ISSUE TO FIX HERE!!!!!!!
					    		
					    		//int globalCount = Integer.parseInt(strTemp);
					    		
					    		//int globalCount = Integer.parseInt(line);
					    		
					    		//MAKE FILENAME WITH THIS INTEGER
					    		
			    		
					    		
//*******************************************************************************************************************				    		
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
							File gfile = new File(globalFile);

							FileOutputStream fos;
							try {
								fos = new FileOutputStream(globalFile);
								String initial = String.format("%d", 1);
								fos.write(initial.getBytes());
								fos.close();
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

//******************************************************************************************************************		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		  
				//********************************************************************
				
				//Creating file to write into
				File file = new File(mFile);
								try {
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(majString.getBytes());
					fos.close();	

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
