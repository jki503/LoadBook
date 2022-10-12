package com.loadbook.book.entity.vo;

import static org.springframework.util.Assert.*;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadingBookInformation {

	private Integer readPages;

	@Transient
	private Integer bookPages;

	public ReadingBookInformation(Integer bookPages, Integer readPages) {
		this.bookPages = bookPages;
		validateReadPage(readPages);
		this.readPages = readPages;
	}

	private void validateReadPage(Integer readPage) {
		notNull(readPage, "읽은 페이지 수는 null일 수 없습니다.");
		isTrue(readPage >= 0 && readPage <= this.bookPages, "읽은 페이지 수는 전체 페이지를 넘길 수 없습니다.");
	}

}
