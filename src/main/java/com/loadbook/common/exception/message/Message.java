package com.loadbook.common.exception.message;

import lombok.Getter;

@Getter
public enum Message {

	// 500
	INTERNAL_SERVER_ERROR("서버 내부 오류입니다."),

	// 400
	BAD_REQUEST("잘못된 요청입니다.");

	private final String message;

	Message(String message) {
		this.message = message;
	}
}
