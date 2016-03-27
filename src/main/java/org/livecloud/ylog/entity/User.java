package org.livecloud.ylog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.livecloud.ylog.utils.StringHelper;

 
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String password;
	private long level;
	private int sex;
	private String email;
	private String qq;
	private String homepage;
	private Date lastvisit;
	private long status;
	private long postarts;
	private long postcomms;
	private String intro;
	private String ip;
	private String agent;

	private Set<Article> articles = new HashSet<Article>();

	public User(){}
	
	public User(String name, String pass){
		this.name = name;
		this.password = StringHelper.encrypt(pass);
		this.lastvisit = new Date();
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
	
	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public Date getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(Date lastvisit) {
		this.lastvisit = lastvisit;
	}
	
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
	
	public long getPostarts() {
		return postarts;
	}

	public void setPostarts(long postarts) {
		this.postarts = postarts;
	}
	
	public long getPostcomms() {
		return postcomms;
	}

	public void setPostcomms(long postcomms) {
		this.postcomms = postcomms;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	@Override 
	public String toString() {
		return name;
	}
	
	@Override  
	public boolean equals(Object obj){
		if(obj == null) return false;  
        if(this == obj) return true;  
        if(obj instanceof User)  
            if(name.equals(((User)obj).getName()))return true;  
        return false;  
	}	 
		 
	@Override  
	public int hashCode(){ 
		return this.getName().hashCode();
	}
}
