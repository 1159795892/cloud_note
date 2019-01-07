package com.pj.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.service.UserService;
import com.pj.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource(name="userService")
	private UserService userService;
	
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String name,String password,String nick){
		NoteResult<Object> result=userService.addUser(name, password, nick);
		return result;
	}
}
