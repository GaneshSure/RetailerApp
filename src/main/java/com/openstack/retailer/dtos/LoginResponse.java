/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jul 6, 2017
 * 
 */
@Data
public class LoginResponse extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6027995795868608677L;

	private int status;
	private String message;
	private LoggedInUserDTO user;
	
}
