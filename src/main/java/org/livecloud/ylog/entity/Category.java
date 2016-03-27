package org.livecloud.ylog.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150426234117655652L;
	private long id;
	private String name;
	private String intro;
	private long count;

	private Set<Article> articles = new HashSet<Article>();
	
	public Category() {}
	public Category(String name, String intro) {
		this.name = name;
		this.intro = intro;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	public String toString() {
		return "Category:" + name + " " + intro;
	}
}