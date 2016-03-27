package org.livecloud.ylog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.Comment;

public interface CommentMapper {
	
    List<Comment> getCommentsBySubmitDate(RowBounds rowBounds); 
	
	long getCommentCountByArticle(@Param("articleId") long articleId); 
	
	List<Comment> getCommentsByArticle(@Param("articleId") long articleId, RowBounds rowBounds);

    long getCount();

    Comment getComment(@Param("id") long id);

    Comment save(Comment com);

    void delete(Comment c); 
	
}
