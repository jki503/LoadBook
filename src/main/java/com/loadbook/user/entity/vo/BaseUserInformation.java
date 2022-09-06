package com.loadbook.user.entity.vo;

import static lombok.AccessLevel.*;

import java.util.regex.Pattern;

import javax.persistence.Embeddable;

import org.springframework.util.Assert;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class BaseUserInformation {

	private String email;

	private String nickName;

	private void validateEmail(String email) {
		Assert.hasText(email, "이메일은 공백일 수 없습니다.");
		Assert.isTrue(email.length() <= 320, "이메일의 길이 제한을 넘겼습니다.");
		Assert.isTrue(
			Pattern.matches(email, "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"),
			"이메일은 공백일 수 없습니다.");
	}

	private void validateNickName(String nickName) {
		Assert.hasText(nickName, "닉네임은 공백일 수 없습니다.");
		Assert.isTrue(nickName.length() >= 4 && nickName.length() <= 50, "닉네임의 길이는 4자 이상 50글자 이하입니다");
	}

}
