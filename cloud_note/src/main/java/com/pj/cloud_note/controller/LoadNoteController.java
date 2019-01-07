package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId) {
		NoteResult<Note> result = noteService.loadNote(noteId);
		return result;
	}
}
