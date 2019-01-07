package com.pj.cloud_note.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.service.UserService;
import com.pj.cloud_note.util.NoteResult;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	@Resource(name="userService")
	private UserService userservice;
	
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult<User> execute(String name ,String password){
		System.out.println(name+" "+password);
		//调用业务层服务
		NoteResult<User> result = userservice.checkLogin(name, password);
		//把NoteResult<User>对象绑定到session,用于session验证
		//session.setAttribute("name", name);
		return result;
	}
	
}
