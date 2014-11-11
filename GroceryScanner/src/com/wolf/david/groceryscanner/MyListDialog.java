package com.wolf.david.groceryscanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class MyListDialog extends DialogFragment {
	
	public static final int DELETE_ITEM = 1;
	public static final int CHANGE_AMOUNT = 0;
	private int position;
	
	public MyListDialog(int position){
		this.position = position;
		
	}
	
	public interface MyListDialogListener {
        public void onChangeAmountClick(DialogFragment dialog,int position);
        public void onRemoveListItemClick(DialogFragment dialog,int position);
    }
	
	MyListDialogListener mListener;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_overflow_title)
	    	.setItems(R.array.overflow_array, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DELETE_ITEM:
						mListener.onRemoveListItemClick(MyListDialog.this, position);
					case CHANGE_AMOUNT:
						mListener.onChangeAmountClick(MyListDialog.this, position);
						break;

					default:
						break;
					}
					
				}
			});
	        

		
        return builder.create();	
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyListDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MyListDialogListener");
        }
    }
}