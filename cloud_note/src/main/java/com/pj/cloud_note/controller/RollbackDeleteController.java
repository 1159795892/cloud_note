package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.RollBackService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class RollbackDeleteController {
	@Resource(name = "rollBackService")
	private RollBackService rollBackService;
	
	@RequestMapping("/note/delete_rollback.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId) {
		NoteResult<Object> result=rollBackService.rollBackdelete(noteId);
		return result;
	}
}
