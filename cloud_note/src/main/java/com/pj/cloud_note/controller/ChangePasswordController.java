package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.service.UserService;
import com.pj.cloud_note.util.NoteResult;

@Controller
public class ChangePasswordController {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/user/chang_pwd")
	@ResponseBody
	public NoteResult<User> execute(String userId, String password,String newpwd) {
		NoteResult<User> result = userService.chang_password(userId, password,newpwd);
		return result;
	}
}
