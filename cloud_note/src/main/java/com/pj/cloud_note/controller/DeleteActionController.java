package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.ActionService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class DeleteActionController {
	@Resource(name = "actionService")
	private ActionService actionService;

	@RequestMapping("/note/suredeleteAction.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId) {
		NoteResult<Object> result =actionService.DeleteActionNote(noteId);
		return result;
	}
}
