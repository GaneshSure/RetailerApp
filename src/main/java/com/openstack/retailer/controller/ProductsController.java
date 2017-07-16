/**
 * 
 */
package com.openstack.retailer.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openstack.retailer.dtos.CategoryDTO;
import com.openstack.retailer.dtos.ProductDTO;
import com.openstack.retailer.dtos.ProductRequest;
import com.openstack.retailer.entities.CategoryEntity;
import com.openstack.retailer.entities.ProductEntity;
import com.openstack.retailer.modelmapper.GenericModelMapper;
import com.openstack.retailer.services.CategoryService;
import com.openstack.retailer.services.ProductsService;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 29, 2017
 * 
 */
@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	static final Logger logger = LogManager.getLogger(ProductsController.class);
	
	@Autowired
	private ProductsService productsService;

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveProduct(@RequestBody ProductRequest productReq) {
		ProductDTO productDTO = productReq.getData();
		if (productDTO != null) {
			ProductEntity productEntity = GenericModelMapper.map(productDTO, ProductEntity.class);
			productsService.saveOrUpdate(productEntity);
			logger.info("Successfully created product");
			return new ResponseEntity<String>("Successfully created product", HttpStatus.OK);
		}else
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);

	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@RequestBody ProductRequest productReq) {
		ProductDTO productDTO = productReq.getData();
		if (productDTO != null) {
			ProductEntity productEntity = GenericModelMapper.map(productDTO, ProductEntity.class);
			productsService.saveOrUpdate(productEntity);
			logger.info("Successfully updated product");
			return new ResponseEntity<String>("Successfully updated product", HttpStatus.OK);
		}else
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> fetchAllProducts() {
		List<ProductEntity> products = productsService.getAllProducts();
		List<ProductDTO> productDTOs = GenericModelMapper.mapList(products, ProductDTO.class);
		return new ResponseEntity<List<ProductDTO>>(productDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") final String productId) {
		ProductEntity product = productsService.getProductById(Long.valueOf(productId));
		ProductDTO productDTO = GenericModelMapper.map(product, ProductDTO.class);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByCode/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductByCode(@PathVariable("code") final String productCode) {
		ProductEntity product = productsService.getProductByCode(Long.valueOf(productCode));
		ProductDTO productDTO = GenericModelMapper.map(product, ProductDTO.class);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> fetchAllCategories() {
		List<CategoryEntity> categories = categoryService.getAllCategories();
		List<CategoryDTO> categoryDTOs = GenericModelMapper.mapList(categories, CategoryDTO.class);
		return new ResponseEntity<List<CategoryDTO>>(categoryDTOs, HttpStatus.OK);
	}
}
