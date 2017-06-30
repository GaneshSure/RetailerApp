/**
 * 
 */
package com.openstack.retailer.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openstack.retailer.dtos.UserDTO;
import com.openstack.retailer.entities.UserEntity;
import com.openstack.retailer.exception.ResourceNotFoundException;
import com.openstack.retailer.modelmapper.GenericModelMapper;
import com.openstack.retailer.repositories.UserRepository;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 *         Created On Jun 27, 2017
 * 
 */
@Service
@Transactional
public class UserService {

	static final Logger logger = LogManager.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	public void saveOrUpdate(UserEntity user) {
		logger.info(" inside saveOrUpdate " + user.toString());
		userRepository.save(user);
	}

	public UserDTO getUser(final long id) {
		UserEntity userEntity = userRepository.getOne(id);
		return GenericModelMapper.map(userEntity, UserDTO.class);
	}

	public UserEntity getUserByUserId(final String userId) {
		logger.info(" inside getUserByUserId " + userId);
		UserEntity userEntity = userRepository.findByUserName(userId);
		if (userEntity != null) {
			logger.info(" userEntity ~~>  " + userEntity.toString());
		} else {
			throw new ResourceNotFoundException(userId, " Use name not found in Data Base..!");
		}
		return userEntity;
	}

	public List<UserEntity> getUsers() {
		logger.info(" inside getUsers ");
		List<UserEntity> userEntities = userRepository.findAll();
		if (userEntities != null) {
			logger.info(" userEntities size ~~> " + userEntities.size());
		} else {
			logger.info(" ***** else userEntities is null ");
		}
		return userEntities;
	}
	
	public boolean validateMobileNumber(long mobile){
		return userRepository.existsByPhonrNumber(mobile);
	}
}
