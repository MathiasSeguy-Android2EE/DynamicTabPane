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

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateAndTimePickerTuto extends Activity {
	/** * The formatter used */
	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	/** * A label to display new date and time to the user */
	TextView calendarTextView;
	/** * The time object representation used by the class */
	Calendar calendar = Calendar.getInstance();
	/** * The datePicker object */
	DatePickerDialog datePickerDialog;
	/** * The listener for date changed */
	DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// When a date is selected update the calendar object
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			// and the associated textView
			updateLabel();
		}
	};
	/**	 * The timepicker object	 */
	TimePickerDialog timePickerDialog;
	/** * The listener for time changed */
	TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// When a date is selected update the calendar object
			calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			calendar.set(Calendar.MINUTE, minute);
			// And the associated textView
			updateLabel();
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		//instanciate the date picker
		datePickerDialog=new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar
				.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		// Define the button that show the date Picker
		Button btn = (Button) findViewById(R.id.btnDate);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Instanciate the DatePicker using this as context, the dateListener
				// defined above and the current time
				datePickerDialog.show();
			}
		});
		//instanciate the timepicker
		timePickerDialog=new TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true);
		// Define the button that show the time Picker
		btn = (Button) findViewById(R.id.btnTime);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Instanciate the TimePicker using this as context, the timeListener defined
				// above and the current time
				timePickerDialog.show();
			}
		});
		// Define the text view that displays the selected time
		calendarTextView = (TextView) findViewById(R.id.calendarTextView);
		updateLabel();
	}

	/** * Calls by the Time and Date SetListeners */
	private void updateLabel() {
		calendarTextView.setText(fmtDateAndTime.format(calendar.getTime()));
	}
}
