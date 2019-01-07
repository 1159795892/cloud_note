package com.pj.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Book;
import com.pj.cloud_note.service.BookService;
import com.pj.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBooksController {
	@Resource(name = "bookService")
	private BookService bookservice;

	@RequestMapping("/loadBookds.do")
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId) {
		// 调用业务层服务
		NoteResult<List<Book>> result = bookservice.LoadUserBooks(userId);

		return result;
	}

}
