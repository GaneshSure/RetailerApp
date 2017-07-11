/**
 * 
 */
package com.openstack.retailer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.openstack.retailer.dtos.ExceptionResponse;
import com.openstack.retailer.exception.ResourceNotFoundException;
import com.openstack.retailer.exception.RetailerException;
import com.openstack.retailer.exception.UnAuthorizedException;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 28, 2017
 * 
 */
@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ExceptionResponse> unAuthorized(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(RetailerException.class)
	public ResponseEntity<ExceptionResponse> handleException(RetailerException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
