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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openstack.retailer.dtos.CategoryDTO;
import com.openstack.retailer.dtos.GetApiListResponse;
import com.openstack.retailer.dtos.GetApiResponse;
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
		} else
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@RequestBody ProductRequest productReq) {
		ProductDTO productDTO = productReq.getData();
		if (productDTO != null) {
			ProductEntity productEntity = GenericModelMapper.map(productDTO, ProductEntity.class);
			productsService.saveOrUpdate(productEntity);
			logger.info("Successfully updated product");
			return new ResponseEntity<String>("Successfully updated product", HttpStatus.OK);
		} else
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> fetchAllProducts() {
		List<ProductEntity> products = productsService.getAllProducts();
		List<ProductDTO> productDTOs = GenericModelMapper.mapList(products, ProductDTO.class);
		return new ResponseEntity<List<ProductDTO>>(productDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GetApiResponse getProductById(@PathVariable("id") final String productId) {
		
		GetApiResponse getApiResponse = new GetApiResponse();
		
		ProductEntity product = productsService.getProductById(Long.valueOf(productId));
		
		if (product != null) {
			ProductDTO productDTO = GenericModelMapper.map(product, ProductDTO.class);
			getApiResponse.setStatus(HttpStatus.OK.value());
			getApiResponse.setMessage("Product details for " + productId);
			getApiResponse.setData(productDTO);
		} else {
			getApiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			getApiResponse.setMessage("No data found for product Id " + productId);
		}
		
		return getApiResponse;
	}

	@RequestMapping(value = "/getProductByCode/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GetApiResponse getProductByCode(@PathVariable("code") final String productCode) {
		
		GetApiResponse getApiResponse = new GetApiResponse();

		ProductEntity product = productsService.getProductByCode(Long.valueOf(productCode));

		if (product != null) {
			ProductDTO productDTO = GenericModelMapper.map(product, ProductDTO.class);
			getApiResponse.setStatus(HttpStatus.OK.value());
			getApiResponse.setMessage("Product details for " + productCode);
			getApiResponse.setData(productDTO);
		} else {
			getApiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			getApiResponse.setMessage("No data found for product code " + productCode);
		}

		return getApiResponse;
	}

	@RequestMapping(value = "/getProductByCategory/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GetApiListResponse getProductByCategoryId(@PathVariable("categoryId") final String categoryId) {
		
		GetApiListResponse getApiListResponse = new GetApiListResponse();

		List<ProductEntity> product = productsService.getProductByCtgryId(Long.valueOf(categoryId));

		if (product != null) {
			List<ProductDTO> productDTO = GenericModelMapper.mapList(product, ProductDTO.class);
			getApiListResponse.setStatus(HttpStatus.OK.value());
			getApiListResponse.setMessage("Product details for " + categoryId);
			getApiListResponse.setData(productDTO);
		} else {
			getApiListResponse.setStatus(HttpStatus.NOT_FOUND.value());
			getApiListResponse.setMessage("No data found for category code " + categoryId);
		}

		return getApiListResponse;
	}
	
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GetApiListResponse fetchAllCategories() {
		GetApiListResponse listResponse = new GetApiListResponse();
		List<CategoryEntity> categories = categoryService.getAllCategories();
		if (categories != null) {
			List<CategoryDTO> categoryDTOs = GenericModelMapper.mapList(categories, CategoryDTO.class);
			listResponse.setStatus(HttpStatus.OK.value());
			listResponse.setMessage("All Categories");
			listResponse.setData(categoryDTOs);
		} else {
			listResponse.setStatus(HttpStatus.NOT_FOUND.value());
			listResponse.setMessage("No data found for Categories");
		}
		return listResponse;
	}
}
