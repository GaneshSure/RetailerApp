/**
 * 
 */
package com.openstack.retailer.exception;

/**
 * @author Rakesh Muppa <rake.kv93@gmail.com>
 *
 * Created On Jun 28, 2017
 * 
 */
public class UnAuthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1476733146547645828L;
	
	private String resourceId;

    public UnAuthorizedException(String resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}
