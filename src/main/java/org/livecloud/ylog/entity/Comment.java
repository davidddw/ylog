package org.livecloud.ylog.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 652812670523069696L;
	private long id;
	private long authorid;
	private String author;
	private String content;
	private String email;
	private String homepage;
	private Date posttime;
	private String ip;
	private String agent;
	
	private Article article;
	
	public Comment() {}
	
	public Comment(String author, String email, String content) {
		this.author = author;
		this.email = email;
		this.content = content;
		this.posttime = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return content;
	}
}
