package com.loadbook.user.controller.request;

import org.springframework.util.Assert;

import com.loadbook.user.entity.GeneralUser;
import com.loadbook.user.entity.vo.BaseUserInformation;

import lombok.Getter;

@Getter
public class UserRegisterRequest {

	private String email;

	private String password;

	private String nickName;

	public UserRegisterRequest(String email, String password, String nickName) {
		Assert.hasText(email, "이메일은 공백일 수 없습니다.");
		Assert.hasText(password, "패스워드는 공백일 수 없습니다.");
		Assert.hasText(nickName, "닉네임은 공백일 수 없습니다.");
		this.email = email;
		this.password = password;
		this.nickName = nickName;
	}

	public GeneralUser toGeneralUser() {
		return new GeneralUser(
			new BaseUserInformation(this.nickName, this.email),
			password
		);
	}
}
