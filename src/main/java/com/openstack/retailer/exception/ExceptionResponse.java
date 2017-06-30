/**
 * 
 */
package com.openstack.retailer.exception;

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

	private int errorCode;
	private String errorMessage;
}
