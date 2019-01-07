package com.pj.cloud_note.dao;

import com.pj.cloud_note.entity.User;

public interface UserDao {
public User findByName(String name);
public void save(User user);
public User findByUserId(String userId); 
public void dynamicUpdate(User user);
}
