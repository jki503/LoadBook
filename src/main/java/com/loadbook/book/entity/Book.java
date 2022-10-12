package com.loadbook.book.entity;

import static com.loadbook.book.entity.vo.BookType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;
import static org.springframework.util.Assert.*;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.loadbook.book.entity.vo.BaseBookInformation;
import com.loadbook.book.entity.vo.BookType;
import com.loadbook.book.entity.vo.FinishedBookInformation;
import com.loadbook.book.entity.vo.ReadingBookInformation;
import com.loadbook.book.entity.vo.WishBookInformation;
import com.loadbook.common.entity.BaseEntity;
import com.loadbook.user.entity.User;

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

	@Enumerated(STRING)
	private BookType bookType;

	@Column(nullable = true)
	private LocalDate startDate;

	@Column(nullable = true)
	private LocalDate endDate;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(
		foreignKey = @ForeignKey(name = "fk_book_user"),
		name = "user_id"
	)
	private User user;

	// 읽고 싶은 책
	public Book(BaseBookInformation baseBookInformation, WishBookInformation wishBookInformation, String memo,
		User user) {
		this(baseBookInformation, wishBookInformation, null, null, WISH, null, null, user);
	}

	// 읽고 있는 책
	public Book(
		BaseBookInformation baseBookInformation,
		ReadingBookInformation readingBookInformation,
		LocalDate startDate,
		String memo,
		User user
	) {
		this(baseBookInformation, null, readingBookInformation, null, READING, startDate, null, user);
	}

	// 읽은 책
	public Book(
		BaseBookInformation baseBookInformation,
		FinishedBookInformation finishedBookInformation,
		LocalDate startDate,
		LocalDate endDate,
		String memo,
		User user) {
		this(baseBookInformation, null, null, finishedBookInformation, FINISHED, startDate, endDate,
			user);
	}

	public Book(
		BaseBookInformation baseBookInformation,
		WishBookInformation wishBookInformation,
		ReadingBookInformation readingBookInformation,
		FinishedBookInformation finishedBookInformation,
		BookType bookType,
		LocalDate startDate,
		LocalDate endDate,
		User user
	) {
		validateBookType(bookType);
		validateUser(user);
		this.baseBookInformation = baseBookInformation;
		this.wishBookInformation = wishBookInformation;
		this.readingBookInformation = readingBookInformation;
		this.finishedBookInformation = finishedBookInformation;
		this.bookType = bookType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	/**
	 * update current book to another books
	 * */
	public void updateToFinishedBook(FinishedBookInformation finishedBookInformation, LocalDate startDate,
		LocalDate endDate) {
		this.wishBookInformation = null;
		this.readingBookInformation = null;
		this.finishedBookInformation = finishedBookInformation;
		this.bookType = FINISHED;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void updateToReadingBook(ReadingBookInformation readingBookInformation, LocalDate startDate) {
		this.wishBookInformation = null;
		this.readingBookInformation = readingBookInformation;
		this.finishedBookInformation = null;
		this.bookType = READING;
		this.startDate = startDate;
	}

	public void updateToWishBook(WishBookInformation wishBookInformation) {
		this.wishBookInformation = wishBookInformation;
		this.readingBookInformation = null;
		this.finishedBookInformation = null;
		this.bookType = WISH;
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
