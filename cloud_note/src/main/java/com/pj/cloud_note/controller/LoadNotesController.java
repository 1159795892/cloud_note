package com.pj.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	@Resource(name = "noteService")
	private NoteService noteservice;

	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map>> execute(String bookId) {
		NoteResult<List<Map>> result=noteservice.loadBookNotes(bookId);
		return result;
	}
	
}
