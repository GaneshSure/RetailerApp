/**
 * 
 */
package com.openstack.retailer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.retailer.entities.CartEntity;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 30, 2017
 * 
 */
public interface CartInfoRepository extends JpaRepository<CartEntity, Long> {

	List<CartEntity> findByUserId(Long userId);
}
