package com.loadbook.book.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loadbook.book.entity.Book;
import com.loadbook.book.entity.vo.BaseBookInformation;
import com.loadbook.book.entity.vo.BookType;
import com.loadbook.book.entity.vo.ReadingBookInformation;
import com.loadbook.common.annotation.CustomJpaTest;
import com.loadbook.user.entity.GeneralUser;
import com.loadbook.user.entity.vo.BaseUserInformation;
import com.loadbook.user.repository.UserRepository;

@CustomJpaTest
class BookRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	private GeneralUser user = new GeneralUser(new BaseUserInformation("인민영웅", "testUser@test.com"), "Test1234!");

	@BeforeEach
	void setup() {
		user = userRepository.save(user);
	}

	@Test
	public void 책이_제대로_저장되는지() {
		Book wishBook = new Book(
			new BaseBookInformation("isbn123456", "isbn123456789", "김소월",
				"책이 끝내줌", "한빛미디어", 250, "이책은 끝내주는 책이다", null),
			null,
			new ReadingBookInformation(250, 125),
			null,
			BookType.READING,
			null,
			null,
			user);
		bookRepository.save(wishBook);
	}
}