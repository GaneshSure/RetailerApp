/**
 * 
 */
package com.openstack.retailer.entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "PRODUCTS")
@Data
public class ProductEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275225880092339405L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private long id;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_CODE")
	private Long productCode;
	
	@Column(name = "PRODUCT_SIZE")
	private Long productSize;
	
	@Column(name = "PRODUCT_QNTY")
	private Long productQnty;
	
	@Column(name = "PRODUCT_PRICE")
	private BigDecimal productPrice;
	
	@Column(name = "PRODUCT_IMAGE", length = 1024)
	private byte[] productImage;
	
	@Column(name = "ctgry_id")
	private long ctgryId;
}
