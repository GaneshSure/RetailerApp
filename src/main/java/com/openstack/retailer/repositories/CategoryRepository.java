/**
 * 
 */
package com.openstack.retailer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.retailer.entities.CategoryEntity;

/**
 * @author Rakesh <rake.kv93@gmail.com>
 *
 * Created on 16-Jul-2017
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
