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
 * Created On Jun 29, 2017
 * 
 */
@Entity
@Table(name = "CARTINFO")
@Data
public class CartEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4303307733393385436L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CART_ID")
	private Long id;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "PRODUCT_CODE")
	private Long productCode;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "CART_STATUS")
	private String cartStatus;
}
