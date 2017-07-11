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
public class ForgotPwdDTO {

	private long phoneNumber;
	private String newPassword;
}
