package com.example.actionbarsherlockdemo;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

public class MainActivity extends SherlockActivity {

	ListView list;
	ListViewAdapter adapter;
	EditText editsearch;
	String[] fname;
	String[] lname;
	String[] address;
	ArrayList<Student> arraylist = new ArrayList<Student>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Generate sample data
		fname = new String[] { "Ganesh", "Manish", "Sunil", "Anil", "Wajid",
				"Jack" };

		lname = new String[] { "Katikar", "Vedpathak", "Kulkarni", "Dixit",
				"Shaikh", "Hodges" };

		address = new String[] { "Solapur", "Pune", "Nashik", "Mumbai",
				"Bangladesh", "Dubai" };

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < fname.length; i++) {
			Student s = new Student(fname[i], lname[i], address[i]);
			// Binds all strings into an array
			arraylist.add(s);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter(this, arraylist);

		// Binds the Adapter to the ListView
		list.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1985CB")));

		// Locate the EditText in menu.xml
		editsearch = (EditText) menu.findItem(R.id.menu_search).getActionView();

		// Capture Text in EditText
		editsearch.addTextChangedListener(textWatcher);

		// Show the search menu item in menu.xml
		MenuItem menuSearch = menu.findItem(R.id.menu_search);

		menuSearch.setOnActionExpandListener(new OnActionExpandListener() {

			// Menu Action Collapse
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// Empty EditText to remove text filtering
				editsearch.setText("");
				editsearch.clearFocus();
				return true;
			}

			// Menu Action Expand
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				// Focus on EditText
				editsearch.requestFocus();

				// Force the keyboard to show on EditText focus
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
				return true;
			}
		});

		// Show the settings menu item in menu.xml
		MenuItem menuSettings = menu.findItem(R.id.settings);

		// Capture menu item clicks
		menuSettings.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				// Do something here
				Toast.makeText(getApplicationContext(), "Nothing here!",
						Toast.LENGTH_LONG).show();
				return false;
			}

		});
		return true;
	}

	// EditText TextWatcher
	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			
			String text = editsearch.getText().toString()
					.toLowerCase(Locale.getDefault());
			adapter.filter(text);
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		

		}

	};

}
