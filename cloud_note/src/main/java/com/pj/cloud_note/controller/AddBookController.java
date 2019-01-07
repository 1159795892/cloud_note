package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.BookService;
import com.pj.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {
	@Resource(name = "bookService")
	private BookService bookService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId,String bookName) {
		NoteResult<Object> result = bookService.addBook(userId, bookName);
		return result;
	}
}
