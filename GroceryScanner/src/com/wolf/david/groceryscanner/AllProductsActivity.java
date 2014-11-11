package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import com.wolf.david.groceryscanner.MyDialog.MyDialogListener;
import com.wolf.david.groceryscanner.MyListDialog.MyListDialogListener;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class AllProductsActivity extends Activity implements MyListDialogListener, MyDialogListener {
	
	private ListView listview;
	private AllProductsAdapter adapter;
	private ArrayList<Product> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_products);
		listview = (ListView) findViewById(R.id.all_products_list);
		final MyDBHandler handler = new MyDBHandler(this, null, null, 1);
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
}
