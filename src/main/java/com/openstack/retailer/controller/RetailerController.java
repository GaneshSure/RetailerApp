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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openstack.retailer.dtos.UserDTO;
import com.openstack.retailer.dtos.UserRequest;
import com.openstack.retailer.entities.UserEntity;
import com.openstack.retailer.exception.ResourceNotFoundException;
import com.openstack.retailer.exception.RetailerException;
import com.openstack.retailer.exception.UnAuthorizedException;
import com.openstack.retailer.modelmapper.GenericModelMapper;
import com.openstack.retailer.services.UserService;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 27, 2017
 * 
 */
@RestController
@RequestMapping(value = "/retailer")
public class RetailerController {

	static final Logger logger = LogManager.getLogger(RetailerController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/saveUser", produces = { "application/json" }, consumes = {
			"application/json" })
	public ResponseEntity<String> saveUser(@RequestBody UserRequest userReq) {
		if (userReq != null) {
			UserDTO userDTO = userReq.getData();
			userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			logger.info("Inside saveUser ~~> " + userDTO.getUserName());
			UserEntity userEntity = GenericModelMapper.map(userDTO, UserEntity.class);
			userService.saveOrUpdate(userEntity);
			return new ResponseEntity<String>(" Successfully created user ", HttpStatus.OK);
		}else
			return new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserRequest userReq) {
		ResponseEntity<String> responseEntity = null;
		if (userReq != null) {
			UserDTO user = userReq.getData();
			logger.info("Inside updateUser ~~> " + user.getUserName());
			UserEntity userEntity = userService.getUserByUserId(user.getUserName());
			if (userEntity != null) {
				GenericModelMapper.mapTo(user, userEntity);
				userService.saveOrUpdate(userEntity);
				responseEntity = new ResponseEntity<String>(" Successfully updated User details ", HttpStatus.OK);
			} else
				new ResourceNotFoundException(user.getUserName(), " Username not found, Please register...!");
		} else
			responseEntity = new ResponseEntity<String>(" Request Object is Empty ", HttpStatus.NOT_ACCEPTABLE);
		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getUsers", produces = { "application/json" })
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		logger.info("Inside getAllUsers ~~> ");
		return new ResponseEntity<List<UserEntity>>(userService.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getUser/{userId}")
	public ResponseEntity<UserEntity> getUserDetails(@PathVariable("userId") String userId) {
		logger.info("Inside getUserDetails ~~> " + userId);
		return new ResponseEntity<UserEntity>(userService.getUserByUserId(userId), HttpStatus.OK);
	}

	/**
	 * This API used to validate the login credentials based on mobile/password
	 * @param {@UserRequest} userReq
	 * @return Success/Failure message.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String login(@RequestBody UserRequest userReq) {
		UserDTO userForm = userReq.getData();
		logger.info("Inside login ~~> " + userForm.getUserName());
		UserEntity userEntity = userService.getUserByUserId(userReq.getData().getUserName());
		logger.info(" Is password matched with DB ==> "
				+ new BCryptPasswordEncoder().matches(userForm.getPassword(), userEntity.getPassword()));
		if (userForm.getPhonrNumber() == userEntity.getPhonrNumber()
				&& new BCryptPasswordEncoder().matches(userForm.getPassword(), userEntity.getPassword())) {
			return "Log in Success";// new ResponseEntity<String>("Log in
									// Success",HttpStatus.OK);
		} else {
			throw new UnAuthorizedException(userForm.getUserName(), "Please enter valid mobile number and password..!");
		}

	}

	/**
	 * This API is used to validate the Phone number for forgot password scenario. 
	 * If user enters valid mobile number then user is allowed to update the new password else not allowed.
	 * @param mobile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/validateMobile/{mobile}")
	public @ResponseBody String validatePhone(@PathVariable("mobile") long mobile) {
		logger.info("Inside getUserDetails ~~> " + mobile);
		if (userService.validateMobileNumber(mobile))
			return " Valid mobile number";
		else
			throw new RetailerException(String.valueOf(mobile), " Invalid mobile number");
	}

	/**
	 * This API is used to test whether the Application is running or not. 
	 * @param name - can be empty or some user name as query param eg: /greeting?name= Rakesh Muppa
	 * @return Hello World ..!
	 */
	@RequestMapping(value = "/greeting", method =RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		logger.info(" default test API ~~> " + name);
		return new ResponseEntity<String>(" Hello  " + name + " ..!", HttpStatus.OK);
	}

}
