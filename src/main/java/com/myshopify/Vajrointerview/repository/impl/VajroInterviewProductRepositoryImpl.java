package com.myshopify.Vajrointerview.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import com.myshopify.Vajrointerview.Entity.ProductCount;
import com.myshopify.Vajrointerview.Entity.ShopInfoEntity;
import com.myshopify.Vajrointerview.repository.VajroInterviewProductRepository;

@SuppressWarnings("deprecation")
@Repository
public class VajroInterviewProductRepositoryImpl implements VajroInterviewProductRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	private final static Logger logger = LogManager.getLogger(VajroInterviewProductRepositoryImpl.class);

	@Transactional
	@Override
	public ShopInfoEntity getShopInfoData() {
		logger.info("Entered: getShopInfoData");
		final Session session = (Session) entityManager.getDelegate();
		final ShopInfoEntity shopInfoEntity = (ShopInfoEntity) session.createCriteria(ShopInfoEntity.class)
				.uniqueResult();
		
		logger.info("Exited: getShopInfoData");
		return shopInfoEntity;
	}

	@Transactional
	@Override
	@Cacheable(cacheNames  = {"cacheProductCount"}, key="#id")
	public Long getCount(final String id) {
		logger.info("Entered: getCount");
		final Session session = (Session) entityManager.getDelegate();
		final Long count = (Long) session.createCriteria(ProductCount.class)
				.setProjection(Projections.projectionList().add(Projections.property("counts")))
				.add(Restrictions.eq("name", id)).uniqueResult();
		
		
		logger.info("Exited: getCount");
		return count;
	}

	@Transactional
	@Override
	@CachePut(cacheNames = {"cacheProductCount"}, key="#id")
	public void updateCountValue(String id, final Long newCount) throws HttpClientErrorException {
		logger.info("Entered: updateCountValue");
		
		final Session session = (Session) entityManager.getDelegate();
		final Transaction trsctn = session.beginTransaction();
		final String hql = "update ProductCount p set p.counts= :a where p.name= :b";
		int updateEntity = session.createQuery(hql).setString("b", id).setLong("a", newCount).executeUpdate();
		if(updateEntity==0) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR	, "Updating the count failed");
		}
		
		trsctn.commit();
		
		logger.info("Exited: updateCountValue");
		
	}

}
