package com.songiam.www.service;

import java.util.List;

import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

public interface CommentService {

	public void saveComment(String comment, String title, String thumbnail, String subTitle) throws Exception;
	public List<Comment> getAllComments() throws Exception;
	public Comment getComment(String commentIndex) throws Exception;
	public Comment getHistoryComment() throws Exception;
	public void increaseCommentCount(String commentIndex) throws Exception;
	public void removeComment(String commentIndex) throws Exception;
	public void saveContactMessage(ContactMessage contactMessage) throws Exception;
	public void updateComment(String index, String comment, String title, String thumbnail, String subTitle) throws Exception;

}
