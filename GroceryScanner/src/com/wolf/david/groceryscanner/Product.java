package com.wolf.david.groceryscanner;

public class Product {
	
	private int id;
	private String name;
	private String size;
	private String barcode;
	
	public Product(){
		
	}
	
	public Product(String name, String size, String barcode){
		this.name = name;
		this.size = size;
		this.barcode = barcode;
	}
	
	public Product(int id, String name, String size, String barcode){
		this.id = id;
		this.name = name;
		this.size = size;
		this.barcode = barcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
