package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.BookService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class renameBookController {
	@Resource(name = "bookService")
	private BookService bookService;
	@RequestMapping("/book/rename")
	@ResponseBody
	public NoteResult<Object> execute(String bookId,String bookName) {
		NoteResult<Object> result=bookService.rename(bookId, bookName);
		return result;
	}
}
