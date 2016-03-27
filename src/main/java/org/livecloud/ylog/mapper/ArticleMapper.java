package org.livecloud.ylog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.Article;

public interface ArticleMapper {	
    
    List<Article> getAllArticles();
    
    Article getArticle(long articleId);
	
    List<Article> getArticlesByTags(@Param("tagValue") String tagName, RowBounds rowBounds);
	
    List<Article> getArticlesByCategory(@Param("enName") String categoryName, RowBounds rowBounds);
	
	List<Article> getArticlesByCategoryId(@Param("id") long categoryId, RowBounds rowBounds);
	
	long getArticleCountByCategoryId(@Param("id") long categoryId); 
	
	List<Article> getArticlesByCreatedDate(RowBounds rowBounds); 
	
	List<Article> getArticlesByPV(RowBounds rowBounds); 

	List<Article> getPrevArticles(@Param("now") Date currentTime);
	
	List<Article> getNextArticles(@Param("now") Date currentTime);
	
	List<Article> getArticlesInSameTag(@Param("myid") long myId, RowBounds rowBounds);

    long count();

    long updateArticle(Article article);
    
    long addArticle(Article article);

    long deleteArticle(@Param("id") long id);

    void updatePV(Article article);

}
