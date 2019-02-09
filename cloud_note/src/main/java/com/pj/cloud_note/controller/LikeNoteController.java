package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.LikeService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class LikeNoteController {
	@Resource(name = "likeService")
	private LikeService likeService;

	@RequestMapping("/note/suredeletelike.do")
	@ResponseBody
	public NoteResult<Object> execute(String shareId) {
		NoteResult<Object> result =likeService.DeleteLikeNote(shareId);
		return result;
	}
}
