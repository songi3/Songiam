package com.songiam.www.model;

import java.util.Date;

public class Comment {

	int index;
	String title;
	String id;
	Date reg_date;
	Date up_date;
	String comment;
	int like;
	int count;
	String thumbnail;
	String sub_title;
	
	public String getSubTitle() {
		return sub_title;
	}

	public void setSubTitle(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegDate() {
		return reg_date;
	}

	public void setRegDate(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getUpDate() {
		return up_date;
	}

	public void setUpDate(Date up_date) {
		this.up_date = up_date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
