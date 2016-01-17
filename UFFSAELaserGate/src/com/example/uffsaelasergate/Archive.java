package com.example.uffsaelasergate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Archive extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
		
		View autoxBtn = findViewById(R.id.button1);
	    autoxBtn.setOnClickListener(new View.OnClickListener() 
	  {

	           @Override
	           public void onClick(View v) 
	           {
	       	    Intent intent = new Intent(Archive.this, Archive_Autox.class);
	    	    Archive.this.startActivity(intent);
	           }
	});
		
		View skidBtn = findViewById(R.id.button2);
	    skidBtn.setOnClickListener(new View.OnClickListener() 
	  {

	           @Override
	           public void onClick(View v) 
	           {
	       	    Intent intent = new Intent(Archive.this, Archive_Skidpad.class);
	    	    Archive.this.startActivity(intent);
	           }
	});
		
		View accelBtn = findViewById(R.id.button3);
	    accelBtn.setOnClickListener(new View.OnClickListener() 
	  {

	           @Override
	           public void onClick(View v) 
	           {
	       	    Intent intent = new Intent(Archive.this, Archive_Accel.class);
	    	    Archive.this.startActivity(intent);
	           }
	});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
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
