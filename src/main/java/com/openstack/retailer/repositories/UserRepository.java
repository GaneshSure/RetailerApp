/**
 * 
 */
package com.openstack.retailer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openstack.retailer.entities.UserEntity;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 27, 2017
 * 
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserName(String username);
	
	boolean existsByPhoneNumber(Long phoneNumber);
	
	UserEntity findByPhoneNumber(Long phoneNumber);
	
	/*@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.mobile = :mobile")
    boolean existsByName(@Param("mobile") String mobile);*/
}
