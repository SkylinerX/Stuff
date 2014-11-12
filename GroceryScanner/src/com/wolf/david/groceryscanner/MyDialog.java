package com.wolf.david.groceryscanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;


public class MyDialog extends DialogFragment {
	
	public static final String BARCODE = "barcode";
	public static final int ADD_PRODUCT = 0;
	public static final int CHANGE_AMOUNT = 1;
	private String barcode;
	private int position;
	private int type;
	private NumberPicker myAmount;
	
	public interface MyDialogListener {
        public void onAmountValue(DialogFragment dialog,int amount,int position);
    }
	
	MyDialogListener mListener;
	
	public MyDialog(String barcode){
		this.barcode = barcode;
		type = ADD_PRODUCT;
	}
	
	public MyDialog(int position){
		this.position = position;
		type = CHANGE_AMOUNT;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (type) {
		case ADD_PRODUCT:
			builder.setTitle(R.string.dialog_not_in_db_title)
	     	   .setMessage(R.string.dialog_not_in_db_message)
	            .setPositiveButton(R.string.dialog_yes, new OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	             	   Intent intent = new Intent(getActivity(),AddProductActivity.class);
	             	   intent.putExtra(BARCODE,barcode);
	             	   getActivity().startActivityForResult(intent,AllProductsActivity.ADD_PRODUCT_REQUEST);
	                }
	            })
	            .setNegativeButton(R.string.dialog_no, new OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                }
	            });
			break;
			
		case CHANGE_AMOUNT:
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View view = inflater.inflate(R.layout.dialog_number_picker, null);
			builder.setView(view);
			myAmount = (NumberPicker) view.findViewById(R.id.dialog_numberPicker);
			myAmount.setMaxValue(50);
			myAmount.setMinValue(1);
			builder.setTitle(R.string.dialog_change_amount_title).setPositiveButton(R.string.dialog_ok, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						int amount = myAmount.getValue();
						mListener.onAmountValue(MyDialog.this, amount, position);
					}
				})
				.setNegativeButton(R.string.dialog_cancel, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});		
			break;

		default:
			break;
		}
        return builder.create();	
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MyDialogListener");
        }
    }
}