/**
 * 
 */
package com.programming.techie.springngblog.service.Exception;

/**
 * @author crystaldlogan
 *
 */
public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String message) {
		super(message);
	}
}
