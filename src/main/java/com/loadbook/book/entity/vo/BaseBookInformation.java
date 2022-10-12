package com.loadbook.book.entity.vo;

import static lombok.AccessLevel.*;
import static org.springframework.util.Assert.*;

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
		validateISBN(isbn);
		validateISBN13(isbn13);
		validateAuthors(authors);
		validateDescription(description);
		validatePublisher(publisher);
		validateBookPages(bookPages);
		validateDetailLink(detailLink);
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.authors = authors;
		this.description = description;
		this.publisher = publisher;
		this.bookPages = bookPages;
		this.detailLink = detailLink;
	}

	private void validateISBN(String isbn) {
		hasText(isbn, "ISBN은 공백일 수 없습니다.");
		isTrue(isbn.length() == 10, "ISBN의 길이는 10이어야합니다");
	}

	private void validateISBN13(String isbn13) {
		hasText(isbn13, "ISBN13은 공백일 수 없습니다.");
		isTrue(isbn13.length() == 13, "ISBN의 길이는 10이어야합니다");
	}

	private void validateAuthors(String authors) {
		hasText(authors, "작가는 공백일 수 없습니다.");
	}

	private void validateDescription(String description) {
		hasText(description, "책 설명은 공백일 수 없습니다.");
	}

	private void validatePublisher(String publisher) {
		hasText(publisher, "출판사는 공백일 수 없습니다.");
	}

	private void validateBookPages(Integer bookPages) {
		notNull(bookPages, "책페이지는 null일 수 없습니다.");
		isTrue(bookPages > 0, "책페이지는 0보다 커야합니다.");
	}

	private void validateDetailLink(String detailLink) {
		hasText(detailLink, "책의 링크는 공백일 수 없습니다.");
	}
}
