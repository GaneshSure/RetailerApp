/**
 * 
 */
package com.openstack.retailer.dtos;

import java.util.List;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jul 20, 2017
 * 
 */
@Data
public class GetApiListResponse extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -677491569655659222L;

	private int status;
	private String message;
	
	private List<?> data;
}
