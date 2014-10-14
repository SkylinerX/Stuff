package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

	private Button scan;
	private TextView type;
	private TextView result;
	private ArrayList<DummyProducts> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scan 	= (Button) 		findViewById(R.id.button_scan);
		type 	= (TextView) 	findViewById(R.id.textView_type);
		result 	= (TextView) 	findViewById(R.id.textView_result);
		
		products = new ArrayList<DummyProducts>();
		DummyProducts product = new DummyProducts("Gerolsteiner Mineralwasser","4001513000620");
		products.add(product);
		product = new DummyProducts("Canada Dry - Ginger Ale","4260301520010");
		products.add(product);
		
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				scan();
			}
		});
	}
	
	public void scan(){
		(new IntentIntegrator(this)).initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scan = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);

		if (scan!=null) {
			type.setText(scan.getFormatName());
			for(int i = 0; i<products.size();i++){
				if(products.get(i).getBarcode().equals(scan.getContents())){
					result.setText(products.get(i).getName());
					break;
				}
				else{
					//result.setText(scan.getContents());
					result.setText("Barcode not in Database");
				}
			}
			
		}
   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSaveInstanceState(Bundle state) {
		state.putString("type", type.getText().toString());
		state.putString("result", result.getText().toString());
	}
	
	@Override
	public void onRestoreInstanceState(Bundle state) {
		type.setText(state.getString("type"));
		result.setText(state.getString("result"));
	}
}
