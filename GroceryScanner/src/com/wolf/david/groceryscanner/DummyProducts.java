package com.wolf.david.groceryscanner;

public class DummyProducts {
	
	private String name;
	private String barcode;
	
	public DummyProducts(String name, String barcode){
		this.name = name;
		this.barcode = barcode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
