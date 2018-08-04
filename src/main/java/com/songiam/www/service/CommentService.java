package com.songiam.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songiam.www.dao.CommentDAO;
import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO;

	public void saveComment(String contentData, String title, String thumbnail, String subTitle) {
		commentDAO.saveComment(contentData, title, thumbnail, subTitle);
	}

	public List<Comment> getAllComments() {
		return commentDAO.getAllComments();
	}

	public Comment getComment(String commentIndex) {
		return commentDAO.getComment(commentIndex);
	}

	public Comment getHistoryComment() {
		return commentDAO.getHistoryComment();
	}
	public void increaseCommentCount(String commentIndex) {
		commentDAO.increaseCommentCount(commentIndex);
	}
	public void saveContactMessage(ContactMessage contactMessage) {
		commentDAO.saveContactMessage(contactMessage);
	}

	public void updateComment(String index, String contentData, String title, String thumbnail, String subTitle) {
		commentDAO.updateComment(index, contentData, title, thumbnail, subTitle);
	}
	
}
