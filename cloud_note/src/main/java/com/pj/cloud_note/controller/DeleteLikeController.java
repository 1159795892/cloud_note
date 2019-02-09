package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.Like;
import com.pj.cloud_note.service.LikeService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class DeleteLikeController {
	@Resource(name = "likeService")
	private LikeService likeService;

	@RequestMapping("/note/surelike.do")
	@ResponseBody
	public NoteResult<Like> execute(String shareId) {
		NoteResult<Like> result =likeService.LikeNote(shareId);
		return result;
	}
}
