package org.altbeacon.beaconreference;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.altbeacon.beacon.AltBeacon;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.w3c.dom.ls.LSInput;

public class RangingActivity extends Activity {
    protected static final String TAG = "RangingActivity";
    private ArrayList<String[]> beaconList = new ArrayList<String[]>();
    private TextView beaconCount;
    private ListView listView;
    private AllBeaconsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranging);
		beaconCount = new TextView(this);
		listView = (ListView)findViewById(R.id.rangingText);
    	adapter = new AllBeaconsAdapter(getBaseContext(),R.layout.list_item_all_beacons, beaconList);
    	listView.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    	beaconCount.setTextColor(Color.WHITE);
    	beaconCount.setPadding(0, 0, 10, 0);
    	beaconCount.setTypeface(null, Typeface.BOLD);
        menu.add(0, 1, 0, "Beacons: ").setActionView(beaconCount).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override 
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override 
    protected void onPause() {
    	super.onPause();
    	// Tell the Application not to pass off ranging updates to this activity
    	((BeaconReferenceApplication)this.getApplication()).setRangingActivity(null);
    }
    @Override 
    protected void onResume() {
    	super.onResume();
    	// Tell the Application to pass off ranging updates to this activity
    	((BeaconReferenceApplication)this.getApplication()).setRangingActivity(this);
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }    

    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        if (beacons.size() > 0) {
            for (Beacon beacon: beacons) {
            	logToDisplay(new String[]{"UUID: " + beacon.getId1(),"Major: " + beacon.getId2(),"Minor: " + beacon.getId3(),"Distance: " + beacon.getDistance()+" m","RSSI: " + beacon.getRssi()+" db",beacon.getBluetoothAddress()});            	
            }
        }
    }

    private void logToDisplay(final String[] line) {
    	runOnUiThread(new Runnable() {
    	    public void run() {
    	    	beaconCount.setText("Beacons: "+beaconList.size());
    	    	
    	    	beaconList.add(line);
    	    	adapter.replaceWith(beaconList);
    	    	
    	    }
    	});
    }
}
