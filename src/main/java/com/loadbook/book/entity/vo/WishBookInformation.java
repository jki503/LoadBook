package com.loadbook.book.entity.vo;

import static lombok.AccessLevel.*;

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

}
