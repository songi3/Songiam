package com.songiam.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.songiam.www.dao.UserDAO;
import com.songiam.www.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Inject
    private UserDAO dao;
	
	@Override
	public User checkUser(String userId) throws Exception{
		return dao.checkUser(userId);
	};
	@Override
	public User checkUser(String userId, String userPassword) throws Exception{
		return dao.checkUser(userId, userPassword);
	};
}
