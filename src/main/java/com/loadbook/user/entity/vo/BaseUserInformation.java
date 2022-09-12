package com.loadbook.user.entity.vo;

import static lombok.AccessLevel.*;

import javax.persistence.Embeddable;

import org.springframework.util.Assert;

import com.loadbook.common.util.RegexUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class BaseUserInformation {

	private String email;

	private String nickName;

	public BaseUserInformation(String nickName, String email) {
		validateNickName(nickName);
		validateEmail(email);

		this.email = email;
		this.nickName = nickName;
	}

	private void validateEmail(String email) {
		Assert.hasText(email, "이메일은 공백일 수 없습니다.");
		Assert.isTrue(email.length() <= 320, "이메일의 길이 제한을 넘겼습니다.");
		Assert.isTrue(RegexUtil.isEmailPattern(email), "잘못된 이메일 형식입니다.");
	}

	private void validateNickName(String nickName) {
		Assert.hasText(nickName, "닉네임은 공백일 수 없습니다.");
		Assert.isTrue(nickName.length() >= 4 && nickName.length() <= 50, "닉네임의 길이는 4자 이상 50글자 이하입니다");
	}

}
