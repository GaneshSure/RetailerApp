/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 30, 2017
 * 
 */
@Data
public class CartInfoRequest extends BaseDTO {
	
	private static final long serialVersionUID = 8959469453022682880L;

	private CartInfoDTO data;
	private String action;
}
