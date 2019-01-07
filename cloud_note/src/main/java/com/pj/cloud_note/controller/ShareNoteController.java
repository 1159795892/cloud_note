package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class ShareNoteController {
@Resource(name="noteService")
private NoteService noteService;


@RequestMapping("/note/share.do")
@ResponseBody
public NoteResult<Share> execute(String noteId) {
	NoteResult<Share> result=noteService.shareNote(noteId);
	return result;
}
}
