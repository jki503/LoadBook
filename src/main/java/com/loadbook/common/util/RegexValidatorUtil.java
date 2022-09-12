package com.loadbook.common.util;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class RegexValidatorUtil {

	//RFC-822
	private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();
	private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W)[A-Za-z\\d\\W]{8,20}$";

	private RegexValidatorUtil() {
	}

	public static boolean isEmailPattern(String email) {
		return EMAIL_VALIDATOR.isValid(email);
	}

	public static boolean isPasswordPattern(String password) {
		return Pattern.matches(PASSWORD_PATTERN, password);
	}
}
