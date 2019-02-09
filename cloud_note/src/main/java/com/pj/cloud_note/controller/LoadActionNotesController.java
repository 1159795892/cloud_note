package com.pj.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Note;
import com.pj.cloud_note.service.ActionService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class LoadActionNotesController {
	@Resource(name = "actionService")
	private ActionService actionService;

	@RequestMapping("/note/action.do")
	@ResponseBody
	public NoteResult<List<Note>> execute() {
		NoteResult<List<Note>> result =actionService.loadActionNotes();
		return result;
	}
}
