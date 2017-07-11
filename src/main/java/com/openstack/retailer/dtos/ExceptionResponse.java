/**
 * 
 */
package com.openstack.retailer.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 28, 2017
 * 
 */
@Data
@NoArgsConstructor
public class ExceptionResponse {

	private int status;
	private String message;
}
