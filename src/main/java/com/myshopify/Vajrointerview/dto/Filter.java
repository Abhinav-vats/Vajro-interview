package com.myshopify.Vajrointerview.dto;



public class Filter {
	
	private String inStock = "1";
	private String outOfStock = "1";
	private String priceGreaterThan = "0";
	private String priceLessThan = String.valueOf(Integer.MAX_VALUE);
	
	public String getInStock() {
		return inStock;
	}
	public void setInStock(String inStock) {
		this.inStock = inStock;
	}
	public String getOutOfStock() {
		return outOfStock;
	}
	public void setOutOfStock(String outOfStock) {
		this.outOfStock = outOfStock;
	}
	public String getPriceGreaterThan() {
		return priceGreaterThan;
	}
	public void setPriceGreaterThan(String priceGreaterThan) {
		this.priceGreaterThan = priceGreaterThan;
	}
	public String getPriceLessThan() {
		return priceLessThan;
	}
	public void setPriceLessThan(String priceLessThan) {
		this.priceLessThan = priceLessThan;
	}
	
	
	
	

}
