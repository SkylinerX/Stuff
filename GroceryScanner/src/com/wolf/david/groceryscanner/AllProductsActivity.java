package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.wolf.david.groceryscanner.MyDialog.MyDialogListener;
import com.wolf.david.groceryscanner.MyListDialog.MyListDialogListener;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class AllProductsActivity extends Activity implements MyListDialogListener, MyDialogListener {
	
	private ListView listview;
	private AllProductsAdapter adapter;
	private ArrayList<Product> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_products);
		listview = (ListView) findViewById(R.id.all_products_list);
		MyDBHandler handler = new MyDBHandler(this, null, null, 1);
		productList = handler.getAllProducts();
		adapter = new AllProductsAdapter(this, R.layout.list_item_all_products, productList);
		listview.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_products, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_scan:
			(new IntentIntegrator(this)).initiateScan();
			break;
			
		case R.id.action_settings:
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
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
	            refreshList();
			}
			
			if(product !=null){
				handler.increaseProductQuantityByOne(product);
				refreshList();
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
	public void onChangeAmountClick(DialogFragment dialog,int position) {
		MyDialog dialogs = new MyDialog(position);
		dialogs.show(getFragmentManager(), "");
		
	}

	@Override
	public void onRemoveListItemClick(DialogFragment dialog, int position) {
		MyDBHandler handler = new MyDBHandler(getBaseContext(),null,null,1);
    	handler.deleteProductByBarcode(adapter.getItem(position).getBarcode());
    	handler.close();
 	    adapter.remove(adapter.getItem(position));
 	    adapter.notifyDataSetChanged();
 	    
	}

	@Override
	public void onAmountValue(DialogFragment dialog, int amount, int position) {
		MyDBHandler handler = new MyDBHandler(getBaseContext(),null,null,1);
		handler.changeProductQuantity(adapter.getItem(position), amount);
		handler.close();
		adapter.notifyDataSetChanged();
		
	}
	
	public void refreshList(){
		productList.clear();
		MyDBHandler handler = new MyDBHandler(this, null, null, 1);
		productList = handler.getAllProducts();
		adapter = new AllProductsAdapter(this, R.layout.list_item_all_products, productList);
		adapter.notifyDataSetChanged();
	}
}
