/**
 * 
 */
package com.openstack.retailer.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 27, 2017
 * 
 */
@Entity
@Table(name = "RETAIL_REGISTRATIONS")
@Data
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7175079821370660916L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private long id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PHONE_NUMBER", unique = true)
	private long phoneNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "FIRM_NAME")
	private String firmName;

	@Column(name = "VILLAGE_OR_STREET_NAME")
	private String villageName;

	@Column(name = "MONDAL_NAME")
	private String mondalName;
	
	@Column(name = "DISTRICT_NAME")
	private String districtName;
	
	@Column(name = "USER_TYPE")
	private String userRole;
	
	@Column(name = "ADDRESS")
	private String address;
	
}
