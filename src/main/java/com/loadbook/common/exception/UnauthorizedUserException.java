package com.loadbook.common.exception;

public class UnauthorizedUserException extends RuntimeException {

	private final String message;

	public UnauthorizedUserException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
