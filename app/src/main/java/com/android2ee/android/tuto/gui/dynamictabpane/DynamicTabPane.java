/**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong> with the smart contribution of <strong>Julien PAPUT</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br>
 * </br>
 * For any information (Advice, Expertise, J2EE or Android Training, Rates, Business):</br>
 * <em>mathias.seguy.it@gmail.com</em></br>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Séguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br>
 * </br>
 * Pour tous renseignements (Conseil, Expertise, Formations J2EE ou Android, Prestations, Forfaits):</br>
 * <em>mathias.seguy.it@gmail.com</em></br>
 * *****************************************************************************************************************</br>
 * Merci à vous d'avoir confiance en Android2EE les Ebooks de programmation Android.
 * N'hésitez pas à nous suivre sur twitter: http://fr.twitter.com/#!/android2ee
 * N'hésitez pas à suivre le blog Android2ee sur Developpez.com : http://blog.developpez.com/android2ee-mathias-seguy/
 * *****************************************************************************************************************</br>
 * com.android2ee.android.tuto</br>
 * 25 mars 2011</br>
 */
package com.android2ee.android.tuto.gui.dynamictabpane;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class DynamicTabPane extends Activity {
	/** * The tabHost */
	TabHost tabHost;
	/** * The On click Listener */
	private final OnClickListener ocl = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			addTabSpec();
		}
	};
	/** * The Button that adds TabSepc */
	private Button btn;

	@Override
	public void onCreate(Bundle icicle) {
		// Instanciate the activity
		super.onCreate(icicle);
		setContentView(R.layout.main);
		// Instanciate the TabHost
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		// Instanciate the first tabPanel
		TabHost.TabSpec tabSpec = tabHost.newTabSpec("digital");
		tabSpec.setContent(R.id.digital);
		tabSpec.setIndicator("Clock");
		// Adding the first tabPanel
		tabHost.addTab(tabSpec);

		// Instanciate the second tabPanel
		tabSpec = tabHost.newTabSpec("button");
		tabSpec.setContent(R.id.button);
		tabSpec.setIndicator("Button");
		// Adding the second tabPanel
		tabHost.addTab(tabSpec);

		// Adding functionalities to the button: When a clic is done, a new TabPanel is created
		btn = (Button) findViewById(R.id.button);

		btn.setOnClickListener(ocl);
	}

	/**
	 * @param tabHost
	 */
	private void addTabSpec() {
		Toast.makeText(this, "A new tab is created", Toast.LENGTH_LONG).show();
		// Retrieve the TabSpec of the TabHost and creating a new entry
		TabHost.TabSpec spec = tabHost.newTabSpec("analogic");
		// Creating a new content to the tabPanel using TabContent factory
		spec.setContent(new TabHost.TabContentFactory() {
			@Override
			public View createTabContent(String tag) {
				// This method has to return a view, the tag is the one define by TabSpec
				return (new AnalogClock(getApplicationContext()));
			}
		});
		// or using an already defined element:
		// spec.setContent(R.id.digital);
		// Define the Tab label
		spec.setIndicator("Analogic");
		// Adding the new tabPanel to the TabHost
		tabHost.addTab(spec);
	}

}