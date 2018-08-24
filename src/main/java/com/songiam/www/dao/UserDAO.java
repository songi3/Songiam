package com.songiam.www.dao;

import java.util.List;

import com.songiam.www.model.User;


public interface UserDAO {

	public User checkUser(String userId) throws Exception;
	public User checkUser(String userId, String userPassword) throws Exception;
}
