package org.livecloud.ylog.vo;

import java.io.Serializable;
import java.util.Date;

public class ArticleInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String user;
	private String title;
	private String content;
	private String tags;
	private String category;
	private Date date;
	
	public ArticleInfo() {}
	
	public ArticleInfo(String id, String title, String content,String user, String category, String tags) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	
	
}
