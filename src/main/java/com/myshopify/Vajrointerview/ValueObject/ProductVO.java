package com.myshopify.Vajrointerview.ValueObject;

import java.util.Date;
import java.util.List;

public class ProductVO {
	
	private Long id;
	
	private String title;
	
	private Date created_at;
	
	private String status;

	private List<VariantVO> variants;
	
	private List<ImageSrcVO> images;

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

	public List<VariantVO> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantVO> variants) {
		this.variants = variants;
	}

	public List<ImageSrcVO> getImages() {
		return images;
	}

	public void setImages(List<ImageSrcVO> images) {
		this.images = images;
	}
	
	

}
