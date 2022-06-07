package com.myshopify.Vajrointerview.Entity;


public class Variant {

	private Long id;

	private String title;

	private Double price;

	private String compare_at_price;

	private Long inventory_item_id;

	private Integer inventory_quantity;

	private Boolean requires_shipping;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCompare_at_price() {
		return compare_at_price;
	}

	public void setCompare_at_price(String compare_at_price) {
		this.compare_at_price = compare_at_price;
	}

	public Long getInventory_item_id() {
		return inventory_item_id;
	}

	public void setInventory_item_id(Long inventory_item_id) {
		this.inventory_item_id = inventory_item_id;
	}

	public Integer getInventory_quantity() {
		return inventory_quantity;
	}

	public void setInventory_quantity(Integer inventory_quantity) {
		this.inventory_quantity = inventory_quantity;
	}

	public Boolean getRequires_shipping() {
		return requires_shipping;
	}

	public void setRequires_shipping(Boolean requires_shipping) {
		this.requires_shipping = requires_shipping;
	}

}
