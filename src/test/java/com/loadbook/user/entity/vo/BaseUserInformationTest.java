package com.loadbook.user.entity.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("기본 유저 정보 테스트: ")
class BaseUserInformationTest {

	private String correctNickName = "인민영웅";

	private String correctEmail = "test1234@naver.com";

	@Test
	public void 닉네임이_null일_경우() {
		assertThatThrownBy(() -> new BaseUserInformation(null, correctEmail))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 닉네임의_길이가_4미만일_경우() {
		assertThatThrownBy(() -> new BaseUserInformation("오이", correctEmail))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 닉네임의_길이가_50초과일_경우() {
		final String longNickName = "오이오이오이오이오이오이오이오이오이오이오이오이오이오이"
			+ "오이오이오이오이오이오이오이오이오이오이오이오이";

		assertThatThrownBy(() -> new BaseUserInformation(longNickName, correctEmail))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 이메일이_공백일_경우() {
		assertThatThrownBy(() -> new BaseUserInformation(correctNickName, null))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 이메일패턴이_잘못됐을_경우() {
		assertThatThrownBy(() -> new BaseUserInformation(correctNickName, "testgmail.com"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 성공적으로_유저기본정보를_생성할_수_있다() {
		BaseUserInformation information = new BaseUserInformation(correctNickName, correctEmail);
		assertThat(information.getEmail()).isEqualTo(correctEmail);
		assertThat(information.getNickName()).isEqualTo(correctNickName);
	}
}