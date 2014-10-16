package com.wolf.david.groceryscanner;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "productDB.db";
	public static final String TABLE_PRODUCTS = "products";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PRODUCTNAME = "name";
	public static final String COLUMN_SIZE = "size";
	public static final String COLUMN_BARCODE = "barcode";
	

	public MyDBHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS 
				+ "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY,"
				+ COLUMN_PRODUCTNAME + " TEXT,"
				+ COLUMN_SIZE + " TEXT,"
				+ COLUMN_BARCODE + " TEXT"
				+ ")";
		db.execSQL(CREATE_PRODUCTS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		onCreate(db);
		
	}
	
	public void addProduct(Product product){
		ContentValues values = new ContentValues();
		values.put(COLUMN_PRODUCTNAME, product.getName());
		values.put(COLUMN_SIZE, product.getSize());
		values.put(COLUMN_BARCODE, product.getBarcode());
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.insert(TABLE_PRODUCTS, null, values);
		db.close();
	}
	
	public Product findProductByName(String productName){
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = '" + productName + "'";
		return findProductQuery(query);
	}
	
	public Product findProductByBarcode(String barcode){
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_BARCODE + " = '"+barcode+"'";
		return findProductQuery(query);
	}
	
	public Product findProductQuery(String query){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Product product = new Product();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			product.setId(Integer.parseInt(cursor.getString(0)));
			product.setName(cursor.getString(1));
			product.setSize(cursor.getString(2));
			product.setBarcode(cursor.getString(3));
			cursor.close();
		} else {
			product = null;
		}
	        db.close();
	        
		return product;
	}
	
	public boolean deleteProductByName(String productName){
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = '" + productName + "'";
		return deleteProductQuery(query);
	}
	
	public boolean deleteProductByBarcode(String barcode){
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_BARCODE + " = '" + barcode + "'";
		return deleteProductQuery(query);
	}
	
	public boolean deleteProductQuery(String query) {
		
		boolean result = false;

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Product product = new Product();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			product.setId(Integer.parseInt(cursor.getString(0)));
			db.delete(TABLE_PRODUCTS, COLUMN_ID + " =?",
		            new String[] { String.valueOf(product.getId()) });
			cursor.close();
			result = true;
		}
	        db.close();
		return result;
	}
	
	public ArrayList<Product> getAllProducts() {
		String query = "Select * FROM " + TABLE_PRODUCTS;
		SQLiteDatabase db = this.getWritableDatabase();
	    ArrayList<Product> productList = new ArrayList<Product>();

	    Cursor cursor = db.rawQuery(query, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Product product = new Product();
	    	product.setId(Integer.parseInt(cursor.getString(0)));
	    	product.setName(cursor.getString(1));
	    	product.setSize(cursor.getString(2));
	    	product.setBarcode(cursor.getString(3));
	    	productList.add(product);
	    	cursor.moveToNext();
	    }
	    cursor.close();
	    return productList;
	}
	

}
