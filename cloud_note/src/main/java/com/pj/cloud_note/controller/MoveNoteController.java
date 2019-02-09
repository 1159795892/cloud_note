package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class MoveNoteController {
	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/note/move.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId,String bookId) {
		System.out.println("noteId="+noteId);
		System.out.println("bookId="+bookId);
		NoteResult<Object> result=noteService.moveNotes(noteId, bookId);
		return result;
	}
}
