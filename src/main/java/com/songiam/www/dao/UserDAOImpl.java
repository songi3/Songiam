package com.songiam.www.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.songiam.www.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	 @Inject
	 private SqlSession sqlSession;
	 private static final String Namespace = "userMapper";
	
	@Override
	public User checkUser(String userId) throws Exception {
		return sqlSession.selectOne(Namespace+".checkUser", userId);
	}
	
	@Override
	public User checkUser(String userId, String userPassword) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userPassword", userPassword);
		
		return sqlSession.selectOne(Namespace+".checkUserAsPassword", paramMap);
	}
}
