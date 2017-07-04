package com.openstack.retailer.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
public class RetailerApplication {

	static final Logger logger = LogManager.getLogger(RetailerApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("entered application");
		SpringApplication.run(RetailerApplication.class, args);
	}
	
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.openstack.retailer.controller"))
	      
	      .paths(PathSelectors.any())
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
	    ApiInfo apiInfo = new ApiInfoBuilder()
	    		.title("Shopping Cart API's for Retailers Apllication")
	    		.description("REST services are implemented using Spring Boot 'Micro Services' for Shopping Cart software.")
	    		.contact(new Contact("Rakesh Muppa", "http://j2eearchitec.blogspot.com/", "rake.kv93@gmail.com"))
	    		.version("1.0")
	    		.build();
	    return apiInfo;
	}
	
}
