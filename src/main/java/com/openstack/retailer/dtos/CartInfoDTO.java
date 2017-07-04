/**
 * 
 */
package com.openstack.retailer.dtos;

import javax.persistence.Column;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 30, 2017
 * 
 */
@Data
public class CartInfoDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7546652032369093155L;

	private Long id;
	private Long userId;
	private Long productCode;
	private String productName;
	private String cartStatus;
}
