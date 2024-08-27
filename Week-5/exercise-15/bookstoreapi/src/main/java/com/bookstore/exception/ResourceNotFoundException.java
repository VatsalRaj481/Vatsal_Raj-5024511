package com.bookstore.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2424559169018278601L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
