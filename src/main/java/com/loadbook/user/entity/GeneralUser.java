package com.loadbook.user.entity;

import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.util.Assert;

import com.loadbook.user.entity.vo.BaseUserInformation;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "general_user")
@NoArgsConstructor(access = PROTECTED)
public class GeneralUser extends User {

	@Column
	private String password;

	public GeneralUser(BaseUserInformation userInformation, String password) {
		super(userInformation);
		this.password = password;
	}

	private void validatePassword(String password) {
		Assert.hasText(password, "비밀번호가 공백일 수 없습니다.");
	}
}
