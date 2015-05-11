package ftis.unpar.ejuklakapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ModifiedArrayAdapter extends ArrayAdapter{
	ArrayList<String> attributes;
	ArrayList<String> anchor;
	
	public ModifiedArrayAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<String> anchor, ArrayList<String> attributes) {
		super(context, resource, textViewResourceId, anchor);
		this.attributes = attributes;
		this.anchor = anchor;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = super.getView(position, convertView, parent);
		
		int left = 15;
		int top = 5;
		int right = 15;
		int bottom = 5;
		
		if(attributes.get(position).equals("h1") && !anchor.get(position).contains("Lampiran")){
			v.setBackgroundColor(Color.RED);
			v.setPadding(left, top, right, bottom);
		}
		else if(attributes.get(position).equals("h2")){
			v.setBackgroundColor(Color.GRAY);
			v.setPadding(left + 15, top, right, bottom);
		}
		else if(attributes.get(position).equals("h3")){
			v.setPadding(left + 15*2, top, right, bottom);
			v.setBackgroundColor(Color.BLACK);
		}
		else{
			v.setBackgroundColor(Color.BLACK);
			v.setPadding(left, top, right, bottom);
		}
		return v;
	}

}
