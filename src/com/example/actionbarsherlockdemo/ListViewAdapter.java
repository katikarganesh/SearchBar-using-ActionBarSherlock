package com.example.actionbarsherlockdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<Student> studentlist = null;
	private ArrayList<Student> arraylist;

	public ListViewAdapter(Context context, List<Student> studentlist) {
		mContext = context;
		this.studentlist = studentlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<Student>();
		this.arraylist.addAll(studentlist);
	}

	public class ViewHolder {
		TextView fname;
		TextView lname;
		TextView address;
	}

	@Override
	public int getCount() {
		return studentlist.size();
	}

	@Override
	public Student getItem(int position) {
		return studentlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.fname = (TextView) view.findViewById(R.id.id_fname);
			holder.lname = (TextView) view.findViewById(R.id.id_lname);
			holder.address = (TextView) view.findViewById(R.id.id_address);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.fname.setText(studentlist.get(position).getFname());
		holder.lname.setText(studentlist.get(position).getLname());
		holder.address.setText(studentlist.get(position).getAddress());
		


		return view;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		studentlist.clear();
		if (charText.length() == 0) {
			studentlist.addAll(arraylist);
		} 
		else 
		{
			for (Student s : arraylist) 
				
			{
				
				String temp=s.getFname();
				temp=temp+""+s.getLname();
				temp=temp+""+s.getAddress();
				if (temp.toLowerCase(Locale.getDefault()).contains(charText)) 
				{
					studentlist.add(s);
				}
			}
		}
		notifyDataSetChanged();
	}

}
