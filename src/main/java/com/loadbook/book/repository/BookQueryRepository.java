package com.loadbook.book.repository;

import java.util.List;

import com.loadbook.book.entity.Book;
import com.loadbook.book.entity.vo.BookType;

public interface BookQueryRepository {
	List<Book> findBookByBookType(BookType bookType, Long userId);

}
