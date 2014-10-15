package com.wolf.david.groceryscanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class ProductNotInDBDialog extends DialogFragment {
	
	public static final String BARCODE = "barcode";
	private String barcode;
	
	public ProductNotInDBDialog(String barcode){
		this.barcode = barcode;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        return builder.create();
    }
}