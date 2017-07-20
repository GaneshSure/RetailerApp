/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jul 20, 2017
 * 
 */
@Data
public class GetApiResponse extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2359285031949287211L;
	
	private int status;
	private String message;
	
	private Object data;
}
