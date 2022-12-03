package com.loadbook.domain.book.entity;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;
import static org.springframework.util.Assert.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.loadbook.common.entity.BaseEntity;
import com.loadbook.domain.book.entity.vo.BaseBookInformation;
import com.loadbook.domain.book.entity.vo.BookType;
import com.loadbook.domain.book.entity.vo.Duration;
import com.loadbook.domain.book.entity.vo.FinishedBookInformation;
import com.loadbook.domain.book.entity.vo.ReadingBookInformation;
import com.loadbook.domain.book.entity.vo.WishBookInformation;
import com.loadbook.domain.user.entity.User;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@NoArgsConstructor(access = PROTECTED)
public class Book extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Embedded
	private BaseBookInformation baseBookInformation;

	@Embedded
	private WishBookInformation wishBookInformation;

	@Embedded
	private ReadingBookInformation readingBookInformation;

	@Embedded
	private FinishedBookInformation finishedBookInformation;

	@Embedded
	private Duration duration;

	@Enumerated(STRING)
	private BookType bookType;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(
		foreignKey = @ForeignKey(name = "fk_book_user"),
		name = "user_id"
	)
	private User user;

	// 읽고 싶은 책
	private Book(BaseBookInformation baseBookInformation, WishBookInformation wishBookInformation,
		User user) {
		this(baseBookInformation, wishBookInformation, null, null, BookType.WISH, null, user);
	}

	// 읽고 있는 책
	private Book(
		BaseBookInformation baseBookInformation,
		ReadingBookInformation readingBookInformation,
		Duration duration,
		User user
	) {
		this(baseBookInformation, null, readingBookInformation, null, BookType.READING, duration, user);
	}

	// 읽은 책
	private Book(
		BaseBookInformation baseBookInformation,
		FinishedBookInformation finishedBookInformation,
		Duration duration,
		User user
	) {
		this(baseBookInformation, null, null, finishedBookInformation, BookType.FINISHED, duration, user);
	}

	private Book(
		BaseBookInformation baseBookInformation,
		WishBookInformation wishBookInformation,
		ReadingBookInformation readingBookInformation,
		FinishedBookInformation finishedBookInformation,
		BookType bookType,
		Duration duration,
		User user
	) {
		validateBookType(bookType);
		validateUser(user);
		this.baseBookInformation = baseBookInformation;
		this.wishBookInformation = wishBookInformation;
		this.readingBookInformation = readingBookInformation;
		this.finishedBookInformation = finishedBookInformation;
		this.bookType = bookType;
		this.duration = duration;
		this.user = user;
	}

	public static Book createWishBook(
		BaseBookInformation baseBookInformation,
		WishBookInformation wishBookInformation,
		User user) {
		return new Book(baseBookInformation, wishBookInformation, user);
	}

	public static Book createReadingBook(
		BaseBookInformation baseBookInformation,
		ReadingBookInformation readingBookInformation,
		Duration duration,
		User user
	) {
		return new Book(baseBookInformation, readingBookInformation, duration, user);
	}

	public static Book createFinishedBook(
		BaseBookInformation baseBookInformation,
		FinishedBookInformation finishedBookInformation,
		Duration duration,
		User user
	) {
		return new Book(baseBookInformation, finishedBookInformation, duration, user);
	}

	/**
	 * update current book to another books
	 * */
	public void update(FinishedBookInformation finishedBookInformation, Duration duration) {
		this.wishBookInformation = null;
		this.readingBookInformation = null;
		this.finishedBookInformation = finishedBookInformation;
		this.bookType = BookType.FINISHED;
		this.duration = duration;
	}

	public void update(ReadingBookInformation readingBookInformation, Duration duration) {
		this.wishBookInformation = null;
		this.readingBookInformation = readingBookInformation;
		this.finishedBookInformation = null;
		this.bookType = BookType.READING;
		this.duration = duration;
	}

	public void update(WishBookInformation wishBookInformation) {
		this.wishBookInformation = wishBookInformation;
		this.readingBookInformation = null;
		this.finishedBookInformation = null;
		this.bookType = BookType.WISH;
		this.duration = null;
	}

	/**
	 * BaseBookInformation Getter
	 * **/
	public String getIsbn() {
		return this.baseBookInformation.getIsbn();
	}

	public String getIsbn13() {
		return this.baseBookInformation.getIsbn13();
	}

	public String getAuthors() {
		return this.baseBookInformation.getAuthors();
	}

	public String getDescription() {
		return this.baseBookInformation.getDescription();
	}

	public String getPublisher() {
		return this.baseBookInformation.getPublisher();
	}

	public Integer getBookPages() {
		return this.baseBookInformation.getBookPages();
	}

	public String getDetailLink() {
		return this.baseBookInformation.getDetailLink();
	}

	public String getMemo() {
		return this.baseBookInformation.getMemo();
	}

	/**
	 * WishedBook Getter
	 * */
	public Integer getExpectedRating() {
		return this.wishBookInformation.getExpectedRating();
	}

	public String getExpectationReview() {
		return this.wishBookInformation.getExpectationReview();
	}

	/**
	 * ReadingBook Getter
	 * */
	public Integer getReadPages() {
		return this.readingBookInformation.getReadPages();
	}

	/**
	 * FinishedBook Getter
	 * */
	public Integer getRating() {
		return this.finishedBookInformation.getRating();
	}

	/**
	 * validation
	 * */
	private void validateBookType(BookType bookType) {
		notNull(bookType, "책의 종류를 명시해주세요");
	}

	private void validateUser(User user) {
		notNull(user, "유저가 null일 수 없습니다.");
	}
}
