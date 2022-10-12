package com.loadbook.domain.book.repository;

import java.util.List;

import com.loadbook.domain.book.entity.Book;
import com.loadbook.domain.book.entity.vo.BookType;

public interface BookQueryRepository {
	List<Book> findBookByBookType(BookType bookType, Long userId);

}
