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
 * @author Rakesh <rake.kv93@gmail.com>
 *
 * Created on 16-Jul-2017
 */
@Entity
@Table(name = "CATEGORY")
@Data
public class CategoryEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -8250342066130095985L;

	@Id
	@Column(name = "ctgry_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="ctgry_name")
	private String categoryName;
	
	@Column(name="ctgry_image", length = 1024)
	private byte[] categoryImage;
}
