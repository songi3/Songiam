package com.songiam.www.dao;

import java.util.List;

import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

public interface CommentDAO {
	
	public void saveComment(String contentData, String title, String thumbnail, String subTitle) throws Exception;
	public List<Comment> getAllComments() throws Exception;
	public Comment getComment(String commentIndex) throws Exception;
	public Comment getHistoryComment() throws Exception;
	public void increaseCommentCount(String commentIndex) throws Exception;
	public void removeComment(String commentIndex) throws Exception;
	public void saveContactMessage(ContactMessage contactMessage) throws Exception;
	public void updateComment(String index, String contentData, String title, String thumbnail, String subTitle) throws Exception;

}
