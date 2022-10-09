package com.loadbook.book.entity.vo;

import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class BaseBookInformation {

	@Column(nullable = false, length = 10)
	private String isbn;

	@Column(nullable = false, length = 13)
	private String isbn13;

	@Column(nullable = false)
	private String authors;

	@Lob
	private String description;

	@Column(nullable = false)
	private String publisher;

	@Column(nullable = false)
	private Integer bookPages;

	private String detailLink;

	public BaseBookInformation(String isbn, String isbn13, String authors, String description, String publisher,
		Integer bookPages, String detailLink) {
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.authors = authors;
		this.description = description;
		this.publisher = publisher;
		this.bookPages = bookPages;
		this.detailLink = detailLink;
	}
}
