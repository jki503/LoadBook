package com.loadbook.user.controller.request;

public class UserCreateRequest {

	private String email;

	private String password;

	public UserCreateRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
