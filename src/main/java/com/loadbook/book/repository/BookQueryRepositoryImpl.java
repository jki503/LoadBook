package com.loadbook.book.repository;

import static com.loadbook.book.entity.QBook.*;
import static com.loadbook.user.entity.QUser.*;

import java.util.List;

import com.loadbook.book.entity.Book;
import com.loadbook.book.entity.vo.BookType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Book> findBookByBookType(BookType bookType, Long userId) {
		return queryFactory.select(book)
			.from(book)
			.leftJoin(book.user, user)
			.where(
				user.id.eq(userId),
				bookTypeEq(bookType)
			)
			.fetch();
	}

	private BooleanExpression bookTypeEq(BookType bookType) {
		return bookType == null ? null : book.bookType.eq(bookType);
	}
}
