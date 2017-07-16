/**
 * 
 */
package com.openstack.retailer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Rakesh <rake.kv93@gmail.com>
 *
 *         Created on 16-Jul-2017
 */
@Data
public class CategoryDTO extends BaseDTO {
	/**
	* 
	*/
	private static final long serialVersionUID = 3450508264888900050L;

	@JsonProperty(value="categoryId")
	private int id;
	private String categoryName;
	private byte[] categoryImage;
}
