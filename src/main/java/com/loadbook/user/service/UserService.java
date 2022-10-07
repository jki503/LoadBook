package com.loadbook.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loadbook.user.controller.request.UserRegisterRequest;
import com.loadbook.user.entity.GeneralUser;
import com.loadbook.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public void register(UserRegisterRequest request) {
		userRepository.findByEmail(request.getEmail())
			.ifPresent(a -> {
				throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
			});

		GeneralUser user = request.toGeneralUser();
		user.hashPassword(passwordEncoder);
		userRepository.save(user);
	}

}
