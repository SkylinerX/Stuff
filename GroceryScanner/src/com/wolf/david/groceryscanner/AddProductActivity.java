package com.wolf.david.groceryscanner;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddProductActivity extends Activity {
	
	private EditText 	productNameTextView;
	private EditText 	packageSizeTextView;
	private TextView 	barcodeEditText;
	private Button 		sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		
		productNameTextView = (EditText)findViewById(R.id.addProduct_Name);
		packageSizeTextView = (EditText)findViewById(R.id.addProduct_package);
		barcodeEditText 	= (TextView) findViewById(R.id.addProduct_barcode);
		sendButton 			= (Button) findViewById(R.id.addProduct_button_send);
		
		barcodeEditText.setText(getIntent().getExtras().getString(ProductNotInDBDialog.BARCODE));
		
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(productNameTextView.getText().toString().isEmpty()||
						packageSizeTextView.getText().toString().isEmpty()){
					Toast.makeText(getBaseContext(), "Please fill out all the Fields", Toast.LENGTH_SHORT).show();
				} else {
					MyDBHandler dbHandler = new MyDBHandler(getBaseContext(), null, null, 1);
					Product product = new Product(productNameTextView.getText().toString(), 
												  packageSizeTextView.getText().toString(),
												  barcodeEditText.getText().toString());
			    	
			    	   dbHandler.addProduct(product);
			    	   
			    	   Intent returnIntent = new Intent();
			    	   
			    	   if(dbHandler.findProductByBarcode(barcodeEditText.getText().toString()) !=null){
			    		   returnIntent.putExtra("result","Database Successfully Updated");
			    		   setResult(RESULT_OK,returnIntent);
			    	   }
			    	   else{
			    		   returnIntent.putExtra("result","Error while updating Database");
			    		   setResult(RESULT_CANCELED,returnIntent);
			    	   }
			    	   
			    	   finish();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product, menu);
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
		state.putString("name", productNameTextView.getText().toString());
		state.putString("size", packageSizeTextView.getText().toString());
	}
	
	@Override
	public void onRestoreInstanceState(Bundle state) {
		productNameTextView.setText(state.getString("name"));
		packageSizeTextView.setText(state.getString("size"));
	}
}
