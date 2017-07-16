/**
 * 
 */
package com.openstack.retailer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openstack.retailer.entities.CategoryEntity;
import com.openstack.retailer.repositories.CategoryRepository;

/**
 * @author Rakesh <rake.kv93@gmail.com>
 *
 *         Created on 16-Jul-2017
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryEntity> getAllCategories() {
		return categoryRepository.findAll();
	}
}
