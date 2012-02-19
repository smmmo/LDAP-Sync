package de.danielweisser.android.ldapsync.administration;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import de.danielweisser.android.ldapsync.R;

public class EditSettings extends TabActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.main);

//	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, EditBasics.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("basics").setIndicator("Basics"
//	                      res.getDrawable(R.drawable.ic_tab_ldapBasic)
	                      )
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, EditMappings.class);
	    spec = tabHost.newTabSpec("mappings").setIndicator("Mappings"
//	                      res.getDrawable(R.drawable.ic_tab_ldapMappings)
	                      )
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    tabHost.setCurrentTab(0);
	}
}
