package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class deleteController {
	@Resource(name = "noteService")
	private NoteService noteService;
	
	@RequestMapping("/note/delete.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId) {
		NoteResult<Object> result = noteService.delectNote(noteId);
		return result;
	}
}
