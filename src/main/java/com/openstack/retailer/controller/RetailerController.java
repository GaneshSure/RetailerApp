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

import com.openstack.retailer.dtos.ForgotPwdRequest;
import com.openstack.retailer.dtos.LoggedInUserDTO;
import com.openstack.retailer.dtos.LoginResponse;
import com.openstack.retailer.dtos.SuccessResponse;
import com.openstack.retailer.dtos.UserDTO;
import com.openstack.retailer.dtos.UserRequest;
import com.openstack.retailer.entities.UserEntity;
import com.openstack.retailer.exception.ResourceNotFoundException;
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

	@RequestMapping(method = RequestMethod.POST, value = "/signup", produces = { "application/json" }, consumes = {
			"application/json" })
	public ResponseEntity<?> saveUser(@RequestBody UserRequest userReq) {
		SuccessResponse response = new SuccessResponse();
		if (userReq != null) {
			UserDTO userDTO = userReq.getData();
			userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
			logger.info("Inside saveUser ~~> " + userDTO.getUserName());
			UserEntity userEntity = GenericModelMapper.map(userDTO, UserEntity.class);
			try {
				userService.saveOrUpdate(userEntity);
				response.setStatus(HttpStatus.OK.value());
				response.setMessage("sign-up success");

			} catch (Exception e) {
				logger.info(" Exception while saving user ~~> " + e.getMessage());
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setMessage(e.getMessage());
			}

		} else {
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			response.setMessage(" Request Object is Empty ");
		}
		return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
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
	 * 
	 * @param {@UserRequest}
	 * 			userReq
	 * @return Success/Failure message.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody LoginResponse login(@RequestBody UserRequest userReq) {
		UserDTO userForm = userReq.getData();
		LoginResponse response = null;
		logger.info("Inside login ~~> " + userForm.getPhoneNumber());
		UserEntity userEntity = userService.getUserByUserId(userReq.getData().getUserName());

		if (userForm.getPhoneNumber() == userEntity.getPhoneNumber()) {
			if (new BCryptPasswordEncoder().matches(userForm.getPassword(), userEntity.getPassword())) {
				LoggedInUserDTO userDTO = new LoggedInUserDTO();
				logger.info(" Is password matched with DB ==> "
						+ new BCryptPasswordEncoder().matches(userForm.getPassword(), userEntity.getPassword()));
				response = new LoginResponse();
				response.setStatus(200);
				response.setMessage("Sign-in Success");
				userDTO.setUserId(userEntity.getId());
				userDTO.setName(userEntity.getUserName());
				userDTO.setUserRole(userEntity.getUserRole());
				response.setUser(userDTO);
			} else
				throw new UnAuthorizedException(userForm.getUserName(), "Authentication Error..!");
		} else {
			response = new LoginResponse();
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setMessage("Please Register First..!");
		}
		return response;
	}

	/**
	 * This API is used to validate the Phone number for forgot password
	 * scenario. If user enters valid mobile number then user is allowed to
	 * update the new password else not allowed.
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/forgotpwd")
	public @ResponseBody SuccessResponse forgotPassword(@RequestBody final ForgotPwdRequest request) {
		logger.info("Inside forgotPassword ~~> " + request.getData().getPhoneNumber());
		SuccessResponse response = new SuccessResponse();
		if (userService.validateMobileNumber(request.getData().getPhoneNumber())) {
			UserEntity user = userService.getUserByPhoneNumber(request.getData().getPhoneNumber());
			if (user != null) {
				user.setPassword(new BCryptPasswordEncoder().encode(request.getData().getNewPassword()));
				userService.saveOrUpdate(user);
			}
			response.setStatus(HttpStatus.OK.value());
			response.setMessage(" Password Updated.. ");
		} else {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setMessage(" Please Register First ");
		}
		return response;
	}

	/**
	 * This API is used to test whether the Application is running or not.
	 * 
	 * @param name
	 *            - can be empty or some user name as query param eg:
	 *            /greeting?name= Rakesh Muppa
	 * @return Hello World ..!
	 */
	@RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		logger.info(" default test API ~~> " + name);
		return new ResponseEntity<String>(" Hello  " + name + " ..!", HttpStatus.OK);
	}

}
