package com.wolf.david.groceryscanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class MyDialog extends DialogFragment {
	
	public static final String BARCODE = "barcode";
	private static final int ADD_PRODUCT = 0;
	private static final int DELETE_PRODUCT = 1;
	private String barcode;
	private int position;
	private AllProductsAdapter adapter;
	private int type;
	
	public MyDialog(String barcode){
		this.barcode = barcode;
		type = ADD_PRODUCT;
	}
	
	public MyDialog(String barcode, int position, AllProductsAdapter adapter){
		this.barcode = barcode;
		this.adapter = adapter;
		this.position = position;
		type = DELETE_PRODUCT;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (type) {
		case ADD_PRODUCT:
			builder.setTitle(R.string.dialog_not_in_db_title)
	     	   .setMessage(R.string.dialog_not_in_db_message)
	            .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	             	   Intent intent = new Intent(getActivity(),AddProductActivity.class);
	             	   intent.putExtra(BARCODE,barcode);
	             	   startActivityForResult(intent,2);
	                }
	            })
	            .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                }
	            });
			break;
		case DELETE_PRODUCT:
			builder.setTitle(R.string.dialog_remove_item_from_db_title)
	     	   .setMessage(R.string.dialog_remove_item_from_db_message)
	            .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	MyDBHandler handler = new MyDBHandler(getActivity().getBaseContext(),null,null,1);
	                	handler.deleteProductByBarcode(barcode);
	             	    adapter.remove(adapter.getItem(position));
	             	    adapter.notifyDataSetChanged();
	                }
	            })
	            .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                }
	            });
			break;
		default:
			break;
		}
        return builder.create();	
    }
}