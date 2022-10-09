package com.loadbook.book.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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

	@Enumerated
	private BookType bookType;

	@Column(nullable = true)
	private LocalDate startDate;

	@Column(nullable = true)
	private LocalDate endDate;

	@Lob
	@Column(nullable = true)
	private String memo;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(
		foreignKey = @ForeignKey(name = "fk_book_user"),
		name = "user_id"
	)
	private User user;

	// 읽고 싶은 책
	public Book(BaseBookInformation baseBookInformation, WishBookInformation wishBookInformation, String memo,
		User user) {
		this(baseBookInformation, wishBookInformation, null, null, BookType.WISH, null, null, memo, user);
	}

	// 읽고 있는 책
	public Book(
		BaseBookInformation baseBookInformation,
		ReadingBookInformation readingBookInformation,
		LocalDate startDate,
		String memo,
		User user
	) {
		this(baseBookInformation, null, readingBookInformation, null, BookType.READING, startDate, null, memo, user);
	}

	// 읽은 책
	public Book(
		BaseBookInformation baseBookInformation,
		FinishedBookInformation finishedBookInformation,
		LocalDate startDate,
		LocalDate endDate,
		String memo,
		User user) {
		this(baseBookInformation, null, null, finishedBookInformation, BookType.FINISHED, startDate, endDate, memo,
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
		String memo,
		User user
	) {
		this.baseBookInformation = baseBookInformation;
		this.wishBookInformation = wishBookInformation;
		this.readingBookInformation = readingBookInformation;
		this.finishedBookInformation = finishedBookInformation;
		this.bookType = bookType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memo = memo;
		this.user = user;
	}
}
