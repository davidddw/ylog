package org.livecloud.ylog.vo;

public class CommentInfo {
	private String submitter;
	private String email;
	private String homepage;
	private String content;
	private int postId;
	
	public CommentInfo() {}
	
	public CommentInfo(String submitter, String email ,String homepage ,String content) {
		this.submitter = submitter;
		this.email = email;
		this.homepage = homepage;
		this.content = content;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	private String validateCode;
	
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
