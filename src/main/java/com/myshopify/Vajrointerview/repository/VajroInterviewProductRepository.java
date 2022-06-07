package com.myshopify.Vajrointerview.repository;

import com.myshopify.Vajrointerview.Entity.ShopInfoEntity;

public interface VajroInterviewProductRepository {
	
	ShopInfoEntity getShopInfoData();
	
	Long getCount(final String id);
	
	void updateCountValue(final String id, final Long newCount);
	
	
	}
