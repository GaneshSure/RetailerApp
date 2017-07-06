/**
 * 
 */
package com.openstack.retailer.dtos;

import com.openstack.retailer.entities.UserEntity;

import lombok.Data;

/**
 * @author Rakesh <rake.kv93@gmail.com>
 *
 * Created on 06-Jul-2017
 */
@Data
public class SuccessResponse extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7185946916910136148L;

	private int status;
	private String message;
	private UserEntity user;
}
