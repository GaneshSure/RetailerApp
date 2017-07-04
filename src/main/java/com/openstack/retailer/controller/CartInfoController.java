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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openstack.retailer.dtos.CartInfoDTO;
import com.openstack.retailer.dtos.CartInfoRequest;
import com.openstack.retailer.entities.CartEntity;
import com.openstack.retailer.modelmapper.GenericModelMapper;
import com.openstack.retailer.services.CartInfoService;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 30, 2017
 * 
 */
@RestController
@RequestMapping("/cartInfo")
public class CartInfoController {

	static final Logger logger = LogManager.getLogger(CartInfoController.class);
	
	@Autowired
	private CartInfoService cartInfoService;
	
	@RequestMapping(value = "/addCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCart(@RequestBody CartInfoRequest cartInfoRequest){
		logger.info(" save cart information starts...");
		if (cartInfoRequest.getData() != null) {
			CartInfoDTO cartInfoDTO = cartInfoRequest.getData();
			cartInfoDTO.setCartStatus("Draft");
			CartEntity cartEntity = GenericModelMapper.map(cartInfoDTO, CartEntity.class);
			cartInfoService.saveOrUpdate(cartEntity);
			logger.info(" Successfully added Order ");
			return new ResponseEntity<String>(" Successfully added Cart ", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/updateCart", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCart(@RequestBody CartInfoRequest cartInfoRequest){
		logger.info(" update cart information starts...");
		String responseMsg = null;
		if (cartInfoRequest.getData() != null) {
			CartInfoDTO cartInfoDTO = cartInfoRequest.getData();
			CartEntity cartEntity = GenericModelMapper.map(cartInfoDTO, CartEntity.class);
			if (cartInfoRequest.getAction().equalsIgnoreCase("submit")) {
				cartEntity.setCartStatus("In-progress");
				cartInfoService.saveOrUpdate(cartEntity);
				responseMsg = " Order has been submitted successfully ";
				logger.info(responseMsg);
				
			}else if (cartInfoRequest.getAction().equalsIgnoreCase("cancel")) {
				cartEntity.setCartStatus("Cancel");
				cartInfoService.saveOrUpdate(cartEntity);
				responseMsg = " Order has been cancelled successfully ";
				logger.info(responseMsg);
			}else if (cartInfoRequest.getAction().equalsIgnoreCase("approve")) {
				cartEntity.setCartStatus("Complete");
				cartInfoService.saveOrUpdate(cartEntity);
				responseMsg = " Order has been approved successfully ";
				logger.info(responseMsg);
			} else if (cartInfoRequest.getAction().equalsIgnoreCase("reject")) {
				cartEntity.setCartStatus("Reject");
				cartInfoService.saveOrUpdate(cartEntity);
				responseMsg = " Order has been rejected successfully ";
				logger.info(responseMsg);
			}
			return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/deleteCart", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCart(@RequestBody CartInfoRequest cartInfoRequest){
		logger.info(" delete cart information starts...");
		if (cartInfoRequest.getData() != null) {
			CartInfoDTO cartInfoDTO = cartInfoRequest.getData();
			CartEntity cartEntity = GenericModelMapper.map(cartInfoDTO, CartEntity.class);
			cartInfoService.delete(cartEntity);
			logger.info(" Successfully deleted Cart ");
			return new ResponseEntity<String>(" Successfully deleted Cart ", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value = "/getOrdersByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartInfoDTO>> getOrdersByUserId(@PathVariable("userId") final String userId){
		List<CartEntity> cartEntities = cartInfoService.getCartInfoByUserId(Long.valueOf(userId));
		return new ResponseEntity<List<CartInfoDTO>>(GenericModelMapper.mapList(cartEntities, CartInfoDTO.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOrdersById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartInfoDTO> getOrderById(@PathVariable("id") final String id){
		CartEntity cartEntity = cartInfoService.getCartInfo(Long.valueOf(id));
		return new ResponseEntity<CartInfoDTO>(GenericModelMapper.map(cartEntity, CartInfoDTO.class), HttpStatus.OK);
	}
	
}
