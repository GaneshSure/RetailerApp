/**
 * 
 */
package com.openstack.retailer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 29, 2017
 * 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275225880092339405L;

	private long productId;
	private String productName;
	private String productCode;
	private String productSize;
	private String productQnty;
	private String productPrice;
}
