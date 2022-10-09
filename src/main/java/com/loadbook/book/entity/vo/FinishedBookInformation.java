package com.loadbook.book.entity.vo;

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
}
