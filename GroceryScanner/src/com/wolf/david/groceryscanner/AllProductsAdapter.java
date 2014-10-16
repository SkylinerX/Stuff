package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AllProductsAdapter extends ArrayAdapter<Product>{
	
	private ArrayList<Product> list;

	public AllProductsAdapter(Context context, int resource, ArrayList<Product> list) {
		super(context, resource, list);
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Product product = list.get(position);
		
			if (v == null) {
	        	LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.list_item_all_products, null);
	        }
					
		    TextView  id  		= (TextView)  v.findViewById(R.id.list_item_products_id);
		    TextView  name  	= (TextView)  v.findViewById(R.id.list_item_products_name);
		    TextView  size  	= (TextView)  v.findViewById(R.id.list_item_products_package);
		    TextView  barcode  	= (TextView)  v.findViewById(R.id.list_item_products_barcode);
		    TextView  quantity  = (TextView)  v.findViewById(R.id.list_item_products_quantity);

		    if(product != null){
		    	id.setText(product.getId()+"");
			    name.setText(product.getName());
			    size.setText(product.getSize());
			    barcode.setText(product.getBarcode());
			    quantity.setText(product.getQuantity()+"");
		    }  
        
		return v;
	}
}
