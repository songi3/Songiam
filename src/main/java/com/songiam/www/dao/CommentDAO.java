package com.songiam.www.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;

@Repository
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveComment(String commentData, String title, String thumbnail, String subTitle) {
		this.jdbcTemplate.update("insert into comments values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 0, title, subTitle, "wks4j1004",
				new Date(), new Date(), commentData, 0, 0, thumbnail);
	}
	
	public void updateComment(String index, String commentData, String title, String thumbnail, String subTitle) {
		this.jdbcTemplate.update("update comments set title = ?, comment = ?, sub_title = ?, up_date = ?, thumbnail = ? where comments.index = ?", title, commentData, subTitle, new Date(), thumbnail, index );
	}
	
	public void increaseCommentCount(String index) {
		this.jdbcTemplate.update("update comments set count = count + 1 where comments.index = ?", index );
	}

	public void saveContactMessage(ContactMessage contactMessage) {
		this.jdbcTemplate.update("insert into contact_message values (?, ?, ?, ?, ?, ?)", 0, contactMessage.getName(), contactMessage.getEmail(),
				contactMessage.getSubject(), contactMessage.getContent(), new Date());
	}
	
	public List<Comment> getAllComments() {
		return this.jdbcTemplate.query("select * from comments", new RowMapper<Comment>() {

			@Override
			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment();
				comment.setComment(rs.getString("comment"));
				comment.setId(rs.getString("id"));
				comment.setIndex(rs.getInt("index"));
				comment.setRegDate(rs.getDate("reg_date"));
				comment.setUpDate(rs.getDate("up_date"));
				comment.setTitle(rs.getString("title"));
				comment.setThumbnail(rs.getString("thumbnail"));
				comment.setSubTitle(rs.getString("sub_title"));
				comment.setCount(rs.getInt("count"));
				return comment;
			}
		});
	}

	public Comment getComment(String commentIndex) {
		return this.jdbcTemplate.queryForObject("select * from comments where comments.index = ?",
				new Object[] { commentIndex }, new RowMapper<Comment>() {
					public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
						Comment comment = new Comment();
						comment.setComment(rs.getString("comment"));
						comment.setId(rs.getString("id"));
						comment.setIndex(rs.getInt("index"));
						comment.setRegDate(rs.getDate("reg_date"));
						comment.setUpDate(rs.getDate("up_date"));
						comment.setTitle(rs.getString("title"));
						comment.setThumbnail(rs.getString("thumbnail"));
						comment.setSubTitle(rs.getString("sub_title"));
						comment.setCount(rs.getInt("count"));
						return comment;
					}
				});
	}
	
	public Comment getHistoryComment() {
		return this.jdbcTemplate.queryForObject("select * from history", new RowMapper<Comment>() {
					public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
						Comment comment = new Comment();
						comment.setComment(rs.getString("comment"));
						comment.setId(rs.getString("id"));
						comment.setIndex(rs.getInt("index"));
						comment.setRegDate(rs.getDate("reg_date"));
						comment.setUpDate(rs.getDate("up_date"));
						comment.setTitle(rs.getString("title"));
						comment.setThumbnail(rs.getString("thumbnail"));
						comment.setSubTitle(rs.getString("sub_title"));
						comment.setCount(rs.getInt("count"));
						return comment;
					}
				});
	}
}
