package com.loadbook.domain.user.entity.vo;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class SocialUserInformation {

	@Enumerated(STRING)
	private SocialProvider provider;

	private String providerId;

	public SocialUserInformation(SocialProvider provider, String providerId) {
		this.provider = provider;
		this.providerId = providerId;
	}
}
