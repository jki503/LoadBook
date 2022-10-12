package com.loadbook.domain.book.entity.vo;

import static org.springframework.util.Assert.*;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinishedBookInformation {

	private Integer rating;

	public FinishedBookInformation(Integer rating) {
		this.rating = rating;
	}

	private void validateRating(Integer rating) {
		notNull(rating, "평점은 null일 수 없습니다.");
		isTrue(rating >= 0 && rating <= 5, "평점은 0점 이상 5점 이하여야합니다.");
	}
}
