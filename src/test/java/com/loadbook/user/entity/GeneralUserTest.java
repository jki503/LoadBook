package com.loadbook.user.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.loadbook.user.entity.vo.BaseUserInformation;

@DisplayName("일반 유저 생성 중: ")
class GeneralUserTest {

	private BaseUserInformation information = new BaseUserInformation("인민영웅", "test1234@gmail.com");

	private String correctPassword = "Test3526!";

	@Test
	public void 비밀번호가_8자미만일_경우() {
		assertThatThrownBy(() -> new GeneralUser(information, "1234567"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 비밀번호가_최소_대문자1개_소문자1개_문자1개_숫자1개를_포함하지_않을경우() {
		assertThatThrownBy(() -> new GeneralUser(information, "password1!"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 비밀번호가_20자초과일_경우() {
		assertThatThrownBy(() -> new GeneralUser(information, "Test3526!Test3526!Test3526!"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 성공적으로_일반유저를_생성할_수_있다() {
		GeneralUser user = new GeneralUser(information, correctPassword);
		assertThat(user.getUserInformation()).isEqualTo(information);
	}

}