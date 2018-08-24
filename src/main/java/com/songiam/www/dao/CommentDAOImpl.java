package com.songiam.www.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

@Repository
public class CommentDAOImpl implements CommentDAO {

	 @Inject
	 private SqlSession sqlSession;
	 private static final String Namespace = "commentMapper";
	
	@Override
	public void saveComment(String comment, String title, String thumbnail, String subTitle) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("comment", comment);
		paramMap.put("title", title);
		paramMap.put("thumbnail", thumbnail);
		paramMap.put("subTitle", subTitle);
	
		sqlSession.selectOne(Namespace+".saveComment", paramMap);
	}

	@Override
	public List<Comment> getAllComments() throws Exception {
		return sqlSession.selectList(Namespace+".getAllComments");
	}

	@Override
	public Comment getComment(String commentIndex) throws Exception {
		return sqlSession.selectOne(Namespace+".getComment", commentIndex);
	}

	@Override
	public Comment getHistoryComment() throws Exception {
		return sqlSession.selectOne(Namespace+".getHistoryComment");
	}

	@Override
	public void increaseCommentCount(String commentIndex) throws Exception {
		sqlSession.selectOne(Namespace+".increaseCommentCount", commentIndex);
	}

	@Override
	public void saveContactMessage(ContactMessage contactMessage) throws Exception {
		sqlSession.selectOne(Namespace+".saveContactMessage", contactMessage);
	}

	@Override
	public void updateComment(String index, String comment, String title, String thumbnail, String subTitle)
			throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("index", index);
		paramMap.put("comment", comment);
		paramMap.put("title", title);
		paramMap.put("thumbnail", thumbnail);
		paramMap.put("subTitle", subTitle);
		
		sqlSession.selectOne(Namespace+".updateComment", paramMap);
	}
	
	@Override
	public void removeComment(String commentIndex) throws Exception{
	
		sqlSession.selectOne(Namespace+".removeComment", commentIndex);
		
	}

}
