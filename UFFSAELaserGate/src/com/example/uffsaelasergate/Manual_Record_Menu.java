package com.example.uffsaelasergate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Manual_Record_Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual__record__menu);
		
		View autoxBtn = findViewById(R.id.button1);	
	    autoxBtn.setOnClickListener(new View.OnClickListener() 
	    {

	             @Override
	             public void onClick(View v) 
	             {
	         	    Intent intent = new Intent(Manual_Record_Menu.this, Autocross.class);
	         	    Manual_Record_Menu.this.startActivity(intent);
	             }
	  });
	    

		View skidBtn = findViewById(R.id.button2);	
	    skidBtn.setOnClickListener(new View.OnClickListener() 
	    {

	             @Override
	             public void onClick(View v) 
	             {
	         	    Intent intent = new Intent(Manual_Record_Menu.this, Skidpad.class);
	         	    Manual_Record_Menu.this.startActivity(intent);
	             }
	  });
	    
	    
	    
	    
		View accelBtn = findViewById(R.id.button3);	
	    accelBtn.setOnClickListener(new View.OnClickListener() 
	    {

	             @Override
	             public void onClick(View v) 
	             {
	         	    Intent intent = new Intent(Manual_Record_Menu.this, Accel.class);
	         	    Manual_Record_Menu.this.startActivity(intent);
	             }
	  });
	    
	}
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manual__record__menu, menu);
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
