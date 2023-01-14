package com.newbietop.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newbietop.book.domain.Book;
import com.newbietop.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

//단위테스트(controller만 테스트)Filter, ControllerAdvice

@Slf4j
@WebMvcTest//spring에서 class확장이 필요하다 @WebMvcTest안에 @ExtendWith(SpringExtension.class)가 없으면 붙여줘야한다.Junit5에서는 포함되어 있지만 4에는 없다
public class BookControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean //ioc환경에 bean 등록된다. controller환경에 service는 올라오지 않으니 강제로 가짜를 주입하는거다.
	private BookService bookService; 
	
	//BDD mockito패턴 given when then
	@Test
	public void save_테스트() throws Exception {
		//given 테스트를 하기 위한 준비 
		
		Book book = new Book(null,"스프링 따라하기","newbietop"); //던질데이터 준비(given)
		
		String content = new ObjectMapper().writeValueAsString(book); //json으로 변환해서 나타나게 해준다.
		when(bookService.저장하기(book)).thenReturn(new Book(1L,"스프링 따라하기","newbietop")); //stub!
		
		// when (테스트 실행)
		ResultActions resultActions = mockMvc.perform(post("/book")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then (검증)
		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("스프링 따라하기")).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findAll_테스트() throws Exception{
		
		//given
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L,"스프링부트 따라하기","newbietop"));
		books.add(new Book(2L,"리액트 따라하기","newbietop"));
		when(bookService.모두가져오기()).thenReturn(books);
		
		//when
		ResultActions resultActions = mockMvc.perform(get("/book").accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2))).andExpect(jsonPath("$.[0].title").value("스프링부트 따라하기"))
		 			 .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findByid_테스트() throws Exception{
		//given
		Long id = 1L;
		when(bookService.한건가져오기(id)).thenReturn(new Book(1L,"자바 공부하기","newbietop"));
		
		//when
		ResultActions resultActions = mockMvc.perform(get("/book/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.title").value("자바 공부하기")).andDo(MockMvcResultHandlers.print());

	}
}
