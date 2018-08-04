package com.songiam.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songiam.www.dao.UserDAO;
import com.songiam.www.model.User;



@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User checkUser(String id) {
		return userDAO.checkUser(id);
	}
	
	public User checkUser(String id,String password) {
		return userDAO.checkUser(id, password);
	}

}
