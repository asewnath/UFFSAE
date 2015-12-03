package com.example.uffsaelasergate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {

	//things to do: Lock to vertical screen
	//lock buttons with conditions (for the different timers)


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	
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
