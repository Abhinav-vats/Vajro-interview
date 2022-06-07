package com.myshopify.Vajrointerview.Entity;

import java.util.Date;
import java.util.List;

public class Product {

	private Long id;
	private String title;
	private String body_html;
	private String vendor;
	private String product_type;
	private Date created_at;
	private String handle;
	private Date updated_at;
	private Date published_at;
	private Object template_suffix;
	private String status;
	private String published_scope;
	private String tags;
	private String admin_graphql_api_id;
	private List<VariantResponse> variants;
	private List<Option> options;
	private List<Image> images;
	private Image image;
	
	
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
	public String getBody_html() {
		return body_html;
	}
	public void setBody_html(String body_html) {
		this.body_html = body_html;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Date getPublished_at() {
		return published_at;
	}
	public void setPublished_at(Date published_at) {
		this.published_at = published_at;
	}
	public Object getTemplate_suffix() {
		return template_suffix;
	}
	public void setTemplate_suffix(Object template_suffix) {
		this.template_suffix = template_suffix;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPublished_scope() {
		return published_scope;
	}
	public void setPublished_scope(String published_scope) {
		this.published_scope = published_scope;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getAdmin_graphql_api_id() {
		return admin_graphql_api_id;
	}
	public void setAdmin_graphql_api_id(String admin_graphql_api_id) {
		this.admin_graphql_api_id = admin_graphql_api_id;
	}
	public List<VariantResponse> getVariants() {
		return variants;
	}
	public void setVariants(List<VariantResponse> variants) {
		this.variants = variants;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	
	
}
