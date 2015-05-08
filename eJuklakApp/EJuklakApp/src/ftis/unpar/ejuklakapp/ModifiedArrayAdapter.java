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
		
		if(attributes.get(position).equals("h1")){
			v.setBackgroundColor(Color.RED);
		}
		else{
			v.setBackgroundColor(Color.BLACK);
		}
		return v;
	}

}
