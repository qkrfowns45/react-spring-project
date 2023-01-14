package com.newbietop.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

//단위테스트 (DB 관련된 Bean이 Ioc에 등록되면 됨)

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) //가짜 디비로 테스트, Replace.NONE 실제 db로 테스트
@DataJpaTest //Jpa관련된 데이터들만 띄운다.
public class BookRepositoryUnitTest {

	@Autowired
	private BookRepository bookRepository;//ioc에 등록되어 있어서 @Mock을 해줄 필요가 없다.
}
