package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class addNoteController {
	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/note/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId, String bookId, String title) {
		NoteResult<Object> result = noteService.addNote(userId, bookId, title);
		return result;
	}
}
