package com.myshopify.Vajrointerview.Entity;

import java.io.Serializable;

public class ShopInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String domain;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
