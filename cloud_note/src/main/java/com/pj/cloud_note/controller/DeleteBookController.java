package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.BookService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class DeleteBookController {
	@Resource(name = "bookService")
	private BookService bookService;

	@RequestMapping("/note/suredeletebook.do")
	@ResponseBody
	public NoteResult<Object> execute(String bookId) {
		NoteResult<Object> result =bookService.deleteBook(bookId);
		return result;
	}
}
