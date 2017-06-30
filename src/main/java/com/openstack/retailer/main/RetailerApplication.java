package com.openstack.retailer.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 27, 2017
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.openstack.*")
@EnableJpaRepositories(basePackages = "com.openstack.retailer.repositories")
@EntityScan(basePackages = "com.openstack.retailer.entities")
public class RetailerApplication {

	static final Logger logger = LogManager.getLogger(RetailerApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("entered application");
		SpringApplication.run(RetailerApplication.class, args);
	}
}
