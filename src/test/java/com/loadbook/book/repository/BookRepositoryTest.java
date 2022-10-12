package com.loadbook.book.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loadbook.book.entity.Book;
import com.loadbook.book.entity.vo.BaseBookInformation;
import com.loadbook.book.entity.vo.BookType;
import com.loadbook.book.entity.vo.ReadingBookInformation;
import com.loadbook.book.entity.vo.WishBookInformation;
import com.loadbook.common.annotation.CustomJpaTest;
import com.loadbook.user.entity.GeneralUser;
import com.loadbook.user.entity.vo.BaseUserInformation;
import com.loadbook.user.repository.UserRepository;

@CustomJpaTest
@DisplayName("Book Repository Test: ")
class BookRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	private GeneralUser user = new GeneralUser(new BaseUserInformation("인민영웅", "testUser@test.com"), "Test1234!");

	@BeforeEach
	void setup() {
		user = userRepository.save(user);
		saveWishBooks(3);
		saveReadingBooks(3);
		saveFinishedBooks(3);
	}

	@Test
	public void 책이_제대로_저장되는지() {
		Book readingBook = new Book(
			new BaseBookInformation("isbn123456", "isbn123456789", "김소월",
				"책이 끝내줌", "한빛미디어", 250, "이책은 끝내주는 책이다", null),
			null,
			new ReadingBookInformation(250, 125),
			null,
			BookType.READING,
			null,
			null,
			user);
		bookRepository.save(readingBook);
	}

	private void saveWishBooks(int count) {
		for (int i = 0; i < count; i++) {
			bookRepository.save(
				new Book(
					new BaseBookInformation("isbn123456", "isbn123456789", "김소월",
						"책이 끝내줌", "한빛미디어", 250, "이책은 끝내주는 책이다", null),
					new WishBookInformation(9, null),
					null,
					null,
					BookType.WISH,
					null,
					null,
					user
				)
			);
		}
	}

	private void saveReadingBooks(int count) {
		for (int i = 0; i < count; i++) {
			bookRepository.save(
				new Book(
					new BaseBookInformation("isbn123456", "isbn123456789", "김소월",
						"책이 끝내줌", "한빛미디어", 250, "이책은 끝내주는 책이다", null),
					null,
					new ReadingBookInformation(250 + i, 125),
					null,
					BookType.READING,
					LocalDate.of(2022, 9, 11),
					null,
					user
				)
			);
		}
	}

	private void saveFinishedBooks(int count) {
		for (int i = 0; i < count; i++) {
			bookRepository.save(
				new Book(
					new BaseBookInformation("isbn123456", "isbn123456789", "김소월",
						"책이 끝내줌", "한빛미디어", 250, "이책은 끝내주는 책이다", null),
					null,
					new ReadingBookInformation(250 + i, 125),
					null,
					BookType.FINISHED,
					LocalDate.of(2022, 9, 11),
					LocalDate.of(2022, 10, 1),
					user
				)
			);
		}
	}

	@Nested
	@DisplayName("책의 리스트를 불러오는 중 - ")
	class DescribeOfFindBooks {

		@Test
		public void 읽고싶은책을_불러올_수_있다() {
			List<Book> wishBooks = bookRepository.findBookByBookType(BookType.WISH, user.getId());
			assertThat(wishBooks.size()).isEqualTo(3);
		}

		@Test
		public void 읽고있는책을_불러올_수_있다() {
			List<Book> readingBooks = bookRepository.findBookByBookType(BookType.READING, user.getId());
			assertThat(readingBooks.size()).isEqualTo(3);
		}

		@Test
		public void 읽은책을_불러올_수_있다() {
			List<Book> finishedBooks = bookRepository.findBookByBookType(BookType.FINISHED, user.getId());
			assertThat(finishedBooks.size()).isEqualTo(3);
		}

		@Test
		public void 전채책_리스트를_불러올_수_있다() {
			List<Book> allBooks = bookRepository.findBookByBookType(null, user.getId());
			assertThat(allBooks.size()).isEqualTo(9);
		}
	}
}