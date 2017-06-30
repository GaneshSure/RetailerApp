/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 28, 2017
 * 
 */
@Data
public class UserRequest extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3154378062990851438L;
	private UserDTO data;
}
