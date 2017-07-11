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
public class ForgotPwdRequest extends BaseDTO{

	private static final long serialVersionUID = 6559709174721691711L;

	private ForgotPwdDTO data;
}
