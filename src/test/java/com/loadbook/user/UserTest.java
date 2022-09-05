package com.loadbook.user;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("유저(User) domain 검증: ")
class UserTest {


	@Test
	public void 이메일이_NULL_일때() {
		Assertions.assertThatThrownBy(
			() -> new User(" ", "인민영웅", "Test1234!")
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void 이메일이_잘못된_양식일때() {
		Assertions.assertThatThrownBy(
			() -> new User("222@.com", "인민영웅", "Test1234!")
		).isInstanceOf(IllegalArgumentException.class);
	}

}