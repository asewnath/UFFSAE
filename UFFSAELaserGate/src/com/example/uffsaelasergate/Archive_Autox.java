package com.example.uffsaelasergate;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import android.R.string;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Archive_Autox extends Activity {
	
	Context context;
	String baseFolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive__autox);
		
		
		
		Context context = getApplicationContext();
		CharSequence text = "null bundle";
		CharSequence text1 = "1";
		CharSequence text2 = "2";
		CharSequence text3 = "3";
		CharSequence text4 = "4";
		CharSequence text5 = "5";
		
		int duration = Toast.LENGTH_SHORT;
		//String filename;
		
		Toast toast = Toast.makeText(context, text, duration);
		Toast toast1 = Toast.makeText(context, text1, duration);
		Toast toast2 = Toast.makeText(context, text2, duration);
		Toast toast3 = Toast.makeText(context, text3, duration);   
		Toast toast4 = Toast.makeText(context, text4, duration);
		Toast toast5 = Toast.makeText(context, text5, duration);
		
		TextView printFile = (TextView)findViewById(R.id.textView1);
		    
		/*
		 * Find filename:
		 
		 		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){	
					baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
				}
				else{
					baseFolder = context.getFilesDir().getAbsolutePath();
				}
				
				filename = "Test.txt"
				
				String mFile = baseFolder + filename;
				
				//Creating file to write into
				File file = new File(mFile);
								try {
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(majString.getBytes());
					fos.close();	
				
		 */
		    try{

		    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){	
					baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
				}
				else{
					baseFolder = context.getFilesDir().getAbsolutePath();
				}
		    	
		    	String filename = "Test.txt";
				String mFile = baseFolder + filename;
		    	
		    	
		    	try{
				
		    		FileInputStream fis = null;
		    		try{
		    			fis = new FileInputStream(mFile);
		    		}
		    		catch(FileNotFoundException e){
		    			toast1.show();
		    		}
				
		    		InputStreamReader isr = new InputStreamReader(fis);
		    		BufferedReader br = new BufferedReader(isr);
		    		
		    		String test;
		    		int count = 0;
		    		
		    		try{
		    			while((test = br.readLine())!= null){
		    				count++;
		    			}
		    		}
		    		catch(IOException e){
		    			toast2.show();
		    		}
		    		try{
		    			fis.getChannel().position(0);
		    		}
		    		catch(IOException e){
		    			toast3.show();
		    		}
		    		
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
		    			toast4.show();
		    		}

		    		//I have an array of strings that I need to convert to
		    		//a single array. I do this by looping through my array
		    		
		    		String strTemp = "";
		    		
		    		for(int j = 0; j < array.length; j++){
		    			strTemp = strTemp + array[j];
		    			strTemp = strTemp + "\n";
		    		}
		    		printFile.setText(strTemp);
				}
				catch(Exception e){
				}
		    	
		    }catch(Exception e){
		    }
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive__autox, menu);
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
