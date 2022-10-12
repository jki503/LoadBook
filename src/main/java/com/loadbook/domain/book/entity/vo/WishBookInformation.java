package com.loadbook.domain.book.entity.vo;

import static lombok.AccessLevel.*;
import static org.springframework.util.Assert.*;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class WishBookInformation {

	private Integer expectedRating;

	private String expectationReview;

	public WishBookInformation(Integer expectedRating, String expectationReview) {
		this.expectedRating = expectedRating;
		this.expectationReview = expectationReview;
	}

	private void validateExpectedRating(Integer expectedRating) {
		notNull(expectedRating, "기대 지수는 null일 수 없습니다.");
		isTrue(expectedRating > 0 && expectedRating <= 10, "기대 지수는 0보다 크고 10이하여야합니다.");
	}
}
