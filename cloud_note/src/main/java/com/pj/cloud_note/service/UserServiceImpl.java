package com.pj.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.cloud_note.dao.UserDao;
import com.pj.cloud_note.entity.User;
import com.pj.cloud_note.util.NoteException;
import com.pj.cloud_note.util.NoteResult;
import com.pj.cloud_note.util.NoteUtil;

@Service("userService") // 扫描的Spring容器
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao;

	public NoteResult<User> checkLogin(String name, String password) {
		// 接受结果数据
		NoteResult<User> result = new NoteResult<User>();
		User user = userDao.findByName(name);
		// 检测用户名
		if (user == null) {
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		// 检测密码
		try {
			String md5Password = NoteUtil.md5(password);
			if (!user.getCn_user_password().equals(md5Password)) {
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		// 用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		System.out.println(user);
		return result;

	}

	public NoteResult<Object> addUser(String name, String password, String nick) {
		NoteResult<Object> result = new NoteResult<Object>();
		// 用户检测
		User hasUser = userDao.findByName(name);
		if (hasUser != null) {
			result.setStatus(1);
			result.setMsg("用户名存在");
			return result;
		}
		User user = new User();
		// 设置用户名
		user.setCn_user_name(name);
		String md5Password;
		// 创建密码
		try {
			md5Password = NoteUtil.md5(password);
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		// 设置密码
		user.setCn_user_password(md5Password);
		// 设置用户昵称
		user.setCn_user_nick(nick);
		// 创建ID
		String id = NoteUtil.createId();
		// 设置用户ID
		user.setCn_user_id(id);
		userDao.save(user);
		// 构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}

	public NoteResult<User> chang_password(String userId, String password,String newpwd) {
		User user = userDao.findByUserId(userId);
		NoteResult<User> result = new NoteResult<User>();
		// 检测密码
		try {
			String md5Password = NoteUtil.md5(password);
			String md5Newpwd = NoteUtil.md5(newpwd);
			if (!user.getCn_user_password().equals(md5Password)) {
				result.setStatus(1);
				return result;
			}
			user.setCn_user_id(userId);
			user.setCn_user_password(md5Newpwd);
			userDao.dynamicUpdate(user);
			result.setStatus(0);
			result.setMsg("修改成功");
			return result;
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		
	}

}
