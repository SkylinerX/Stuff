package com.wolf.david.groceryscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

//	private Button scan;
//	private TextView type;
//	private TextView result;
	
	private ImageView scanBarcode;
	private ImageView shoppingList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_dash);
		
//		scan 	= (Button) 		findViewById(R.id.button_scan);
//		type 	= (TextView) 	findViewById(R.id.textView_type);
//		result 	= (TextView) 	findViewById(R.id.textView_result);
		scanBarcode = (ImageView) findViewById(R.id.dash_imageview_barcode);
		shoppingList = (ImageView) findViewById(R.id.dash_imageview_shopping_cart);
		
//		scan.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				scan();
//			}
//		});
		
		scanBarcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				scan();
				
			}
		});
		
		shoppingList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,AllProductsActivity.class);
				startActivity(intent);	
			}
		});
		
	}
	
	public void scan(){
		(new IntentIntegrator(this)).initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		IntentResult scan = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);

		if (scan!=null) {
			//type.setText(scan.getFormatName());
			
			MyDBHandler handler = new MyDBHandler(getBaseContext(), null, null, 1);
			Product product = handler.findProductByBarcode(scan.getContents());
			
			if(scan.getContents() !=null && product == null){
				MyDialog dialog = new MyDialog(scan.getContents());
	            dialog.show(getFragmentManager(), "");
			}
			
			if(product !=null){
			}
//			if(product != null){
//				result.setText(product.getName());
//			}
//			else{
//				MyDialog dialog = new MyDialog(scan.getContents());
//	            dialog.show(getFragmentManager(), "");
//			}
		}
		else{
			//type.setText("Error while scanning, please try again");
			Toast.makeText(getBaseContext(), "Error while scanning, please try again", Toast.LENGTH_SHORT).show();
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
		int id = item.getItemId();
		if (id == R.id.action_view_database) {
			Intent intent = new Intent(this,AllProductsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	@Override
//	public void onSaveInstanceState(Bundle state) {
//		state.putString("type", type.getText().toString());
//		state.putString("result", result.getText().toString());
//	}
//	
//	@Override
//	public void onRestoreInstanceState(Bundle state) {
//		type.setText(state.getString("type"));
//		result.setText(state.getString("result"));
//	}
}
