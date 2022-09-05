package com.loadbook.user;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user")
public class User {


	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "email", length = 320, nullable = false)
	private String email;

	@Column(name = "nick_name", length = 50, nullable = false)
	private String nickName;

	@Column
	private String password;

	public User(String email, String nickName, String password) {
		validateEmail(email);
		validateNickName(nickName);
		validatePassword(password);
		this.email = email;
		this.nickName = nickName;
		this.password = password;
	}

	private void validateEmail(String email){
		Assert.hasText(email,"이메일은 공백일 수 없습니다.");
		Assert.isTrue(email.length() <=320, "이메일의 길이 제한을 넘겼습니다.");
		Assert.isTrue(Pattern.matches(email,"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"), "이메일은 공백일 수 없습니다.");
	}

	private void validateNickName(String nickName){
		Assert.hasText(nickName,"닉네임은 공백일 수 없습니다.");
		Assert.isTrue(nickName.length() >=4 && nickName.length() <=50, "닉네임의 길이는 4자 이상 50글자 이하입니다");
	}

	private void validatePassword(String password){
		Assert.hasText(password, "비밀번호가 공백일 수 없습니다.");
	}

}
