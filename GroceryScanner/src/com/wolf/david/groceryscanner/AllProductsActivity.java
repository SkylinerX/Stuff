package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AllProductsActivity extends Activity {
	
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
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, 
					int position, long id) {
				MyDialog dialog = new MyDialog(productList.get(position).getBarcode(),position,adapter);
				dialog.show(getFragmentManager(), "");
				return true;
			}
		});
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
}
