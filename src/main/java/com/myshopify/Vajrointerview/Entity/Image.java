package com.myshopify.Vajrointerview.Entity;

import java.util.Date;
import java.util.List;

public class Image {
	private Long id;
	private Long product_id;
	private Long position;
	private Date created_at;
	private Date updated_at;
	private String alt;
	private Integer width;
	private Integer height;
	private String src;
	private List<Long> variant_ids;
	private String admin_graphql_api_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public List<Long> getVariant_ids() {
		return variant_ids;
	}
	public void setVariant_ids(List<Long> variant_ids) {
		this.variant_ids = variant_ids;
	}
	public String getAdmin_graphql_api_id() {
		return admin_graphql_api_id;
	}
	public void setAdmin_graphql_api_id(String admin_graphql_api_id) {
		this.admin_graphql_api_id = admin_graphql_api_id;
	}
	
	
	
	

}
