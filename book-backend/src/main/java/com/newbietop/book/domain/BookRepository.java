package com.newbietop.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository 적어야 스프링 ioc에 등록이 되는데 JpaRepository를 extends하면 crud를 쓰기만 하면 된다!.
public interface BookRepository extends JpaRepository<Book, Long>{

}
