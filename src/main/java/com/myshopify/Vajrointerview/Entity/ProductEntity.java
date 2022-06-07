package com.myshopify.Vajrointerview.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProductEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String title;
	
	private Date created_at;
	
	private String status;

	
	private List<Variant> variants = new ArrayList<Variant>();
	
	private List<ImageSrc> images = new ArrayList<ImageSrc>();
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public ProductEntity(Long id, String title, Date created_at, String status, List<Variant> variants,List<ImageSrc> images
			) {
		super();
		this.id = id;
		this.title = title;
		this.created_at = created_at;
		this.status = status;
		this.variants = variants;
		this.images = images;
	}
	
	public ProductEntity() {
	}

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
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Variant> getVariants() {
		return variants;
	}
	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}
	public List<ImageSrc> getImages() {
		return images;
	}
	public void setImages(List<ImageSrc> images) {
		this.images = images;
	}
	
	

}
