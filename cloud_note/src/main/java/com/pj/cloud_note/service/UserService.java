package com.pj.cloud_note.service;

import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.util.NoteResult;

public interface UserService {
	public NoteResult<User> checkLogin(String name,String password);
	public NoteResult<Object> addUser(String name,String password,String nick);
	public NoteResult<User> chang_password(String userId,String password,String newpwd);
}
