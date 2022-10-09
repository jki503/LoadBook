package com.loadbook.book.entity.vo;

import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ReadingBookInformation {

	@Column
	private String readPage;

	public ReadingBookInformation(String readPage) {
		this.readPage = readPage;
	}
}
