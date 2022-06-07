package com.myshopify.Vajrointerview.Entity;

import java.io.Serializable;
import java.util.List;

import com.myshopify.Vajrointerview.ValueObject.ProductVO;

public class FinalResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ShopInfo shop_info;
	private Long product_count;
	
	private List<ProductVO> products;
	
	
	
	
	public FinalResponseDTO(List<ProductVO> products, Long product_count, ShopInfo shop_info) {
		super();
		this.products = products;
		this.product_count = product_count;
		this.shop_info = shop_info;
	}
	
	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public Long getProduct_count() {
		return product_count;
	}
	public void setProduct_count(Long product_count) {
		this.product_count = product_count;
	}
	public ShopInfo getShop_info() {
		return shop_info;
	}
	public void setShop_info(ShopInfo shop_info) {
		this.shop_info = shop_info;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
