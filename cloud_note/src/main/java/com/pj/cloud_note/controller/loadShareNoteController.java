package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@Controller
public class loadShareNoteController {
	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/note/load_share")
	@ResponseBody
	public NoteResult<Share> execute(String shareId) {
		NoteResult<Share> result = noteService.loadShareNote(shareId);
		return result;
	}
}
