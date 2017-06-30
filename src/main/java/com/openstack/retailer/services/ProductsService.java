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
		logger.info(" Inside saveOrUpdate " + product.toString());
		productsRepository.save(product);
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
