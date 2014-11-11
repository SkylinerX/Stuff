package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import com.wolf.david.groceryscanner.MyListDialog.MyListDialogListener;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AllProductsAdapter extends ArrayAdapter<Product> {
	
	private ArrayList<Product> list;
	private Product product;
	private Context context;

	public AllProductsAdapter(Context context, int resource, ArrayList<Product> list) {
		super(context, resource, list);
		this.context = context;
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		product = list.get(position);
		
			if (v == null) {
	        	LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.list_item_all_products, null);
	        }
			
			// TODO FIXME
//			if ( position % 2 == 0 ){
//                convertView.setBackground(getContext().getResources().getDrawable(R.drawable.karo_repeat));
//            }else{
//                convertView.setBackgroundResource(0);
//            }
				
			
			Typeface customFont = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/this_is_my_life.ttf");
		    TextView  id  		= (TextView)  v.findViewById(R.id.list_item_products_id);
		    TextView  name  	= (TextView)  v.findViewById(R.id.list_item_products_name);
		    TextView  size  	= (TextView)  v.findViewById(R.id.list_item_products_package);
		    TextView  barcode  	= (TextView)  v.findViewById(R.id.list_item_products_barcode);
		    TextView  quantity  = (TextView)  v.findViewById(R.id.list_item_products_quantity);
		    ImageView overflow	= (ImageView) v.findViewById(R.id.list_item_products_overflow);
		    
		    id.setTypeface(customFont);
		    name.setTypeface(customFont);
		    size.setTypeface(customFont);
		    quantity.setTypeface(customFont);
		    

		    if(product != null){
		    	id.setText(product.getId()+"");
			    name.setText(product.getName());
			    size.setText(product.getSize());
			    barcode.setText(product.getBarcode());
			    quantity.setText(product.getQuantity()+"x");
			    overflow.setTag(R.string.POSITION_TAG,position);
			    
			    
			    overflow.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						MyListDialog dialog = new MyListDialog((Integer)arg0.getTag(R.string.POSITION_TAG));
						dialog.show(((AllProductsActivity)context).getFragmentManager(), null);
						
					}
				});
		    }  
        
		return v;
	}
}
