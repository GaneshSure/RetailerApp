/**
 * 
 */
package com.openstack.retailer.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * @author rmuppa
 *
 * Created On Jun 27, 2017
 * Modified On Jun 27, 2017 - 3:45:17 AM
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157356261569767753L;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date", insertable = true, updatable=false)
	private Timestamp createdOn;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name="updated_date",insertable = false, updatable=true)
	private Timestamp updatedOn;
	
}
