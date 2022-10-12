package com.loadbook.domain.user.entity;

import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import com.loadbook.common.util.RegexValidatorUtil;
import com.loadbook.domain.user.entity.vo.BaseUserInformation;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "general_user")
@NoArgsConstructor(access = PROTECTED)
public class GeneralUser extends User {

	@Column
	private String password;

	public GeneralUser(BaseUserInformation userInformation, String password) {
		super(userInformation);
		validatePassword(password);
		this.password = password;
	}

	private void validatePassword(String password) {
		Assert.isTrue(RegexValidatorUtil.isPasswordPattern(password), "비밀번호 규격을 지켜주세요");
	}

	public GeneralUser hashPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

}
