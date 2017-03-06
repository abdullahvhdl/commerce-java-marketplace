package com.respodo.commerce.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to authenticate.
 */
public class EmailAlreadyExistsException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6553100735466631129L;

	public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable t) {
        super(message, t);
    }
}
