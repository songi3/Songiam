package com.songiam.www.service;

import java.util.List;

import com.songiam.www.model.User;

public interface UserService {

	public User checkUser(String userId) throws Exception;
	public User checkUser(String userId, String userPassword) throws Exception;
}
