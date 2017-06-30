/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 29, 2017
 * 
 */
@Data
public class ProductRequest extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2983729030839464535L;
	private ProductDTO data;
}
