package com.pj.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Share;
import com.pj.cloud_note.service.LikeService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class LoadLikeNotesController {
	@Resource(name = "likeService")
	private LikeService likeService;

	@RequestMapping("/note/like.do")
	@ResponseBody
	public NoteResult<List<Share>> execute() {
		NoteResult<List<Share>> result =likeService.loadLikeNotes();
		return result;
	}
}
