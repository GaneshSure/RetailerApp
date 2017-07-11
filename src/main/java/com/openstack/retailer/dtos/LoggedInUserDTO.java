/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jul 10, 2017
 * 
 */
@Data
public class LoggedInUserDTO {

	private long userId;
	private String name;
	private String userRole;
}
