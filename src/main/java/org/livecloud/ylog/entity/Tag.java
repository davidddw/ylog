package org.livecloud.ylog.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String intro;
	private long count;
	private Set<Article> articles = new HashSet<Article>();
	
	public Tag() {
	}
	
	public Tag(String name) {
		this.name = name;
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
	

	@Override 
	public String toString() {
		return name;
	}
	
	@Override  
	public boolean equals(Object obj){
		if(obj == null) return false;  
        if(this == obj) return true;  
        if(obj instanceof Tag)  
            if(name.equals(((Tag)obj).getName()))return true;  
        return false;  
	}	 
		 
	@Override  
	public int hashCode(){ 
		return this.getName().hashCode();
	}

}
