package com.loadbook.common.exception;

import com.loadbook.common.exception.message.Message;

public class AlreadyExistException extends RuntimeException {

	private final Message message;

	public AlreadyExistException(Message message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message.getMessage();
	}

}
