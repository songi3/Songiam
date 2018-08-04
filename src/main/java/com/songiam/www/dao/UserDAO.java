package com.songiam.www.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.songiam.www.model.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User checkUser(String id) {

		try {
			User user = this.jdbcTemplate.queryForObject("select * from user where id = ?", new Object[] { id },
					new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User user = new User();
							user.setEmail(rs.getString("email"));
							user.setId(rs.getString("id"));
							user.setImage(rs.getString("image"));
							user.setName(rs.getString("name"));
							user.setPassword(rs.getString("password"));
							user.setRegDate(rs.getDate("reg_date"));
							return user;
						}
					});

			return user;
			
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}

	}
	
	public User checkUser(String id, String password) {

		try {
			User user = this.jdbcTemplate.queryForObject("select * from user where id = ? and password = ?", new Object[] { id, password},
					new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User user = new User();
							user.setEmail(rs.getString("email"));
							user.setId(rs.getString("id"));
							user.setImage(rs.getString("image"));
							user.setName(rs.getString("name"));
							user.setPassword(rs.getString("password"));
							user.setRegDate(rs.getDate("reg_date"));
							return user;
						}
					});

			return user;
			
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}

	}
}
