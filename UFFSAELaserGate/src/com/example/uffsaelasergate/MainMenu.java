package com.example.uffsaelasergate;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothAdapter;


public class MainMenu extends Activity {

	//things to do: Lock to vertical screen
	//lock buttons with conditions (for the different timers)
	
	 private static BluetoothSocket mSocket;
	 private static final int REQUEST_CONNECT_DEVICE = 1;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	
		Context context = getApplicationContext();
		String text = "Test";
		int duration = Toast.LENGTH_LONG;
		final Toast toast = Toast.makeText(context, text, duration);
		
	View manualBtn = findViewById(R.id.button4);
    manualBtn.setOnClickListener(new View.OnClickListener() 
  {
           @Override
           public void onClick(View v) 
           {
       	    Intent intent = new Intent(MainMenu.this, Manual_Record_Menu.class);
    	    MainMenu.this.startActivity(intent);
           }
});
    
	View BTBtn = findViewById(R.id.button3);
    BTBtn.setOnClickListener(new View.OnClickListener() 
  {

           @Override
           public void onClick(View v) 
           {

       	    //Intent intent = new Intent(MainMenu.this, Bluetooth.class);
    	    //MainMenu.this.startActivity(intent);

        	   
           	//if (mSocket.isConnected()) {
           		
        	   //toast.show();
           	   
        		// Launch the DeviceListActivity to see devices and do scan
           		
        		Intent serverIntent = new Intent(MainMenu.this, DeviceListActivity.class);
        		startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
				
        	//}
           	
           	
        	//else
            //	if (!mSocket.isConnected()) {

            		//mSerialService.stop();
            		//mSerialService.start();
            		/*
            		try {
						mSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		try {
						mSocket.connect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
		    		
            	//}

           }
});
    
    
	View archiveBtn = findViewById(R.id.button2);
    archiveBtn.setOnClickListener(new View.OnClickListener() 
  {

           @Override
           public void onClick(View v) 
           {
       	    Intent intent = new Intent(MainMenu.this, Archive.class);
    	    MainMenu.this.startActivity(intent);
           }
});

}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
