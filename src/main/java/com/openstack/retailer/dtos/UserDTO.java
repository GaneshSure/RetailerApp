/**
 * 
 */
package com.openstack.retailer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Rakesh Muppa
 *
 * Created On Jun 27, 2017
 * Modified On Jun 27, 2017 - 3:32:16 AM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1867128923703139618L;
	
	private long id;
	private String userName;
	private String password;
	private long phonrNumber;
	private String emailId;
	private String firmName;
	private String villageName;
	private String mondalName;
	private String districtName;
	private String userType;
	
}
