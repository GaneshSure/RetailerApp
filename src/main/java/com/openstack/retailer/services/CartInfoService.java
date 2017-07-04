/**
 * 
 */
package com.openstack.retailer.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.openstack.retailer.entities.CartEntity;
import com.openstack.retailer.exception.ResourceNotFoundException;
import com.openstack.retailer.exception.RetailerException;
import com.openstack.retailer.repositories.CartInfoRepository;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 30, 2017
 * 
 */
@Service
@Transactional
public class CartInfoService {

	static final Logger logger = LogManager.getLogger(CartInfoService.class);
	@Autowired
	private CartInfoRepository cartInfoRepository;

	public void saveOrUpdate(CartEntity cartEntity) {
		try {
			logger.info("Inside saveOrUpdate ---> " + cartEntity.getProductName());
			cartInfoRepository.save(cartEntity);
		} catch (Exception e) {
			logger.error(" Exception occured while save/update cart information " + e.getMessage());
			throw new RetailerException(cartEntity.getProductName(),
					" Something went wrong while save/update cart information");
		}
	}

	public void delete(CartEntity cartEntity) {
		try {
			logger.info("Inside delete ---> " + cartEntity.getProductName());
			cartInfoRepository.delete(cartEntity);
		} catch (Exception e) {
			logger.error(" Exception occured while deleting cart information " + e.getMessage());
			throw new RetailerException(cartEntity.getProductName(),
					" Something went wrong while deleting cart information");
		}
	}

	
	public CartEntity getCartInfo(Long id) {
		logger.info("Inside getCartInfo ==> " + id);
		CartEntity cartEntity = cartInfoRepository.getOne(id);
		if (cartEntity != null) {
			return cartEntity;
		} else {
			logger.info("No data found for id ==> " + id);
			throw new ResourceNotFoundException(String.valueOf(id),
					" Cart Information not found for " + String.valueOf(id));
		}
	}
	
	public List<CartEntity> getCartInfoByUserId(Long userId) {
		logger.info("Inside getCartInfoByUserId ==> " + userId);
		List<CartEntity> cartEntities = cartInfoRepository.findByUserId(userId);
		if (CollectionUtils.isEmpty(cartEntities)) {
			logger.info("No data found for user ==> " + userId);
			throw new ResourceNotFoundException(String.valueOf(userId),
					" Cart Information not found for User : " + String.valueOf(userId));
		} else {
			return cartEntities;
		}
	}
	
	public List<CartEntity> getAllOrders() {
		logger.info("Inside getAllOrders ==> ");
		List<CartEntity> cartEntities = cartInfoRepository.findAll();
		if (CollectionUtils.isEmpty(cartEntities)) {
			logger.info("No data found ==> ");
			throw new ResourceNotFoundException(" There are no orders ");
		} else {
			return cartEntities;
		}
	}

}
