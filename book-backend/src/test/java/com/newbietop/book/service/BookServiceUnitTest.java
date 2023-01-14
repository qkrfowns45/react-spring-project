package com.newbietop.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.newbietop.book.domain.BookRepository;

//단위테스트(service와 관련된 애들만 memory에 띄운다)
//BoardRepository 가짜객체로 만들 수 있다.

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

	@InjectMocks //해당 파일에 @Mock로 등록된 모든 애들을 주입받는다.
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
}
