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

import com.openstack.retailer.entities.ProductEntity;
import com.openstack.retailer.exception.ResourceNotFoundException;
import com.openstack.retailer.exception.RetailerException;
import com.openstack.retailer.repositories.ProductsRepository;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 29, 2017
 * 
 */
@Service
@Transactional
public class ProductsService {

	static final Logger logger = LogManager.getLogger(ProductsService.class.getName());
	
	@Autowired
	private ProductsRepository productsRepository;
	
	
	public void saveOrUpdate(ProductEntity product) {
		try {
			logger.info(" Inside saveOrUpdate " + product.toString());
			productsRepository.save(product);
		} catch (Exception e) {
			logger.error(" Exception occured while save/update Product information " + e.getMessage());
			throw new RetailerException(product.getProductName(),
					" Something went wrong while save/update Product ");
		}
	}
	
	public void delete(ProductEntity productEntity) {
		try {
			logger.info("Inside delete ---> " + productEntity.toString());
			productsRepository.delete(productEntity);
		} catch (Exception e) {
			logger.error(" Exception occured while deleting Product information " + e.getMessage());
			throw new RetailerException(productEntity.getProductName(),
					" Something went wrong while deleting Product information");
		}
	}
	public ProductEntity getProduct(long id){
		return productsRepository.getOne(id);
	}
	
	public ProductEntity getProductByCode(long productCode){
		logger.info(" Inside getProductByCode ~~> " + productCode);
		ProductEntity product = productsRepository.findByProductCode(productCode);
		if (product != null) {
			logger.info("Product entity details ~~> " + product.toString());
			return product;
		} else {
			throw new ResourceNotFoundException(String.valueOf(productCode), " Product not found for productCode = " + String.valueOf(productCode));
		}
	}
	
	public ProductEntity getProductById(long productId){
		logger.info(" Inside getProductById ~~> " + productId);
		ProductEntity product = productsRepository.getOne(productId);
		if (product != null) {
			logger.info("Product entity details ~~> " + product.toString());
			return product;
		} else {
			throw new ResourceNotFoundException(String.valueOf(productId), " Product not found for productId = " + String.valueOf(productId));
		}
	}
	
	public List<ProductEntity> getAllProducts(){
		logger.info(" Inside getAllProducts ~~> ");
		return productsRepository.findAll();
	}
}
