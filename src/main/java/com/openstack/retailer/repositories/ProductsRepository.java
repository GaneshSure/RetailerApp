/**
 * 
 */
package com.openstack.retailer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.retailer.entities.ProductEntity;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 29, 2017
 * 
 */
public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {

	ProductEntity findByProductCode(Long productCode);
}
