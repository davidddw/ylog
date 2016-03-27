package org.livecloud.ylog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
 
public class Article implements Serializable{
	/**
	 * @Transient
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	
	private User user;
	
	private Category category;
	private String title;
	private String content;
	private long pv;
	private Date createdDate;
	private Date modifiedDate;
	private String excerpt;
	private String name;
	private long commentCount;
	private ArticleStatusType articleStatus;
	private CommentStatusType commentStatus;
	private String tagStrings;
	
	private Set<Comment> comments = new HashSet<Comment>();
	private Set<Tag> tags = new HashSet<Tag>();
	
	public enum ArticleStatusType {
        PUBLISH, DRAFT, PRIVATE
	}
	public enum CommentStatusType {
        OPEN, CLOSED, REGISTER
	}
	
	public Article() {}
	public Article(String title ,String content){
		this.title = title;
		this.content = content;
		this.commentCount = 0;
		this.createdDate = new Date();
		this.articleStatus = ArticleStatusType.DRAFT;
		this.commentStatus = CommentStatusType.OPEN;
	}
	
	public String getUserName() {
		return this.getUser().getName();
	}
	
	public String getCategoryName() {
		return this.getCategory().getIntro();
	}

	public String[] getCommentsName() {
		Set<Comment> comments = this.comments;
		return (String[]) comments.toArray(new String[comments.size()]);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	public ArticleStatusType getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(ArticleStatusType articleStatus) {
		this.articleStatus = articleStatus;
	}

	public CommentStatusType getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(CommentStatusType commentStatus) {
		this.commentStatus = commentStatus;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getPv() {
		return pv;
	}
	public void setPv(long pv) {
		this.pv = pv;
	}

	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getTagStrings() {
		return tagStrings;
	}

	public void setTagStrings(String tagStrings) {
		this.tagStrings = tagStrings;
	}

	@Override
	public String toString() {
		return this.title + this.user;
	}
}