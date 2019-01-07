package com.pj.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.service.NoteService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class searchShareController {
@Resource(name="noteService")
private NoteService noteService;

	@RequestMapping("/note/search_share")
	@ResponseBody
	public NoteResult<List<Share>> execute(String keyword ,int page) {
	NoteResult<List<Share>> result =noteService.searchShareNote(keyword, page);
	return result;
}
}
