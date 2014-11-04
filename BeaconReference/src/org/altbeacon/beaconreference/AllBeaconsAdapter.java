package org.altbeacon.beaconreference;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AllBeaconsAdapter extends ArrayAdapter<String[]>{
	
	private ArrayList<String[]> beacons;

	public AllBeaconsAdapter(Context context, int resource, ArrayList<String[]> beacons) {
		super(context, resource, beacons);
		this.beacons = beacons;
	}
	
	public void replaceWith(ArrayList<String[]> newBeacons) {
	    this.beacons.clear();
	    this.beacons.addAll(newBeacons);
	    notifyDataSetChanged();
	  }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		String[] line = beacons.get(position);
		
			if (v == null) {
	        	LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.list_item_all_beacons, null);
	        }
					
		    TextView  uuid  	= (TextView)  v.findViewById(R.id.beacon_UUID);
		    TextView  major		= (TextView)  v.findViewById(R.id.beacon_major);
		    TextView  minor  	= (TextView)  v.findViewById(R.id.beacon_minor);
		    TextView  distance  = (TextView)  v.findViewById(R.id.beacon_distance);
		    TextView  rssi  	= (TextView)  v.findViewById(R.id.beacon_RSSI);

		    if(line != null){
		    	uuid.setText(line[0]);
		    	major.setText(line[1]);
		    	minor.setText(line[2]);
			    distance.setText(line[3]);
			    rssi.setText(line[4]);
		    }  
        
		return v;
	}
	
	@Override
	public void notifyDataSetChanged() {
		
		super.notifyDataSetChanged();
	}
}
