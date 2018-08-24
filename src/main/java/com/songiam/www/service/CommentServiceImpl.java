package com.songiam.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.songiam.www.dao.CommentDAO;
import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
    private CommentDAO dao;

	@Override
	public void saveComment(String comment, String title, String thumbnail, String subTitle) throws Exception {
		dao.saveComment(comment, title, thumbnail, subTitle);
	}

	@Override
	public List<Comment> getAllComments() throws Exception {
		return dao.getAllComments();
	}

	@Override
	public Comment getComment(String commentIndex) throws Exception {
		return dao.getComment(commentIndex);
	}

	@Override
	public Comment getHistoryComment() throws Exception {
		return dao.getHistoryComment();
	}

	@Override
	public void increaseCommentCount(String commentIndex) throws Exception {
		dao.increaseCommentCount(commentIndex);
	}

	@Override
	public void saveContactMessage(ContactMessage contactMessage) throws Exception {
		dao.saveContactMessage(contactMessage);
	}

	@Override
	public void updateComment(String index, String comment, String title, String thumbnail, String subTitle)
			throws Exception {
		dao.updateComment(index, comment, title, thumbnail, subTitle);
	}
	
	@Override
	public void removeComment(String commentIndex) throws Exception{
		dao.removeComment(commentIndex);
	}

}
