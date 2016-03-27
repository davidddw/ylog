package org.livecloud.ylog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.Article;
import org.livecloud.ylog.entity.Category;
import org.livecloud.ylog.entity.Tag;
import org.livecloud.ylog.mapper.ArticleMapper;
import org.livecloud.ylog.mapper.CategoryMapper;
import org.livecloud.ylog.mapper.TagMapper;
import org.livecloud.ylog.mapper.UserMapper;
import org.livecloud.ylog.vo.ArticleInfo;
import org.livecloud.ylog.utils.SetOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.github.pagehelper.PageInfo;

@Service("articleService")
public class ArticleService {
    
    @Autowired
	private ArticleMapper articleMapper;
    
    @Autowired
	private UserMapper userMapper;
    
    @Autowired
	private CategoryMapper categoryMapper;
    
    @Autowired
	private TagMapper tagMapper;

	public Article getArticleFromArticleInfo(ArticleInfo articleInfo) {
		long inputUserId = Long.parseLong(articleInfo.getUser());
		long inputCategoryId = Long.parseLong(articleInfo.getCategory());
		String articleId = articleInfo.getId();
		Article article = new Article(HtmlUtils.htmlEscape(articleInfo.getTitle()),
				articleInfo.getContent());
		article.setUser(userMapper.getUser(inputUserId));
		article.setCategory(categoryMapper.getCategory(inputCategoryId));
		article.setModifiedDate(new Date());

		List<Tag> list = tagMapper.getAllTags();
		article.setTagStrings(HtmlUtils.htmlEscape(articleInfo.getTags()));
		String[] newStrings = articleInfo.getTags().split(",");
		Set<Tag> tags = new HashSet<Tag>();
		for (int i = 0; i < newStrings.length; i++) {
			String escapeString = HtmlUtils.htmlEscape(newStrings[i]);
			Tag t = new Tag(escapeString);
			if (SetOpt.exist(list, escapeString, Tag.class)) {
				t = tagMapper.getOneTagByName(escapeString);
			}
			tags.add(t);
		}
		article.getTags().addAll(tags);
		if (articleId != null) {
			article.setId(Integer.parseInt(articleId));
		}
		return article;
	}

	public long getArticleCount() {
		return articleMapper.count();
	}
	
	public long addNewArticleFromInfo(ArticleInfo articleInfo) {
		return articleMapper.addArticle(getArticleFromArticleInfo(articleInfo));
	}

	public long updateArticleFromInfo(ArticleInfo articleInfo) {
		return articleMapper.updateArticle(getArticleFromArticleInfo(articleInfo));
	}

	public boolean removeArticleById(long articleId) {
		Article article = articleMapper.getArticle(articleId);
		if (article == null) {
			return false;
		} else {
			for (Tag tag : article.getTags()) {
				int articleNumber = tag.getArticles().size();
				tag.setArticles(null);
				if (articleNumber == 1)
					tagMapper.deleteTag(tag.getId());
			}
			article.setTags(null);
			articleMapper.deleteArticle(articleId);
			return true;
		}
	}

	public long updateArticle(Article article) {
		return articleMapper.updateArticle(article);
	}

	public Article findById(long articleId) {
		return articleMapper.getArticle(articleId);
	}

	public List<Article> findAllArticles() {
		return articleMapper.getAllArticles();
	}

	public PageInfo<Article> findLatestArticles(int pageIndex, int pageSize) {
		return new PageInfo<Article>(articleMapper.getArticlesByCreatedDate(
		        new RowBounds(pageIndex, pageSize)));
	}
	
	public PageInfo<Article> findMostPopArticles(int pageIndex, int pageSize) {
		return new PageInfo<Article>(articleMapper.getArticlesByPV(
				new RowBounds(pageIndex, pageSize)));
	}
	
	public long getArticleCountByCategory(long categoryId) {
		return articleMapper.getArticleCountByCategoryId(categoryId);
	}
	
	public PageInfo<Article> findArticlesByCategoryId(long categoryId,
			int pageIndex, int pageSize) {
	    List<Article> list = articleMapper.getArticlesByCategoryId(categoryId, 
		        new RowBounds(pageIndex, pageSize));
		return new PageInfo<Article>(list);
	}

	public PageInfo<Article> findArticlesFromCategory(Category category,
			int pageIndex, int pageSize) {
	    List<Article> list = articleMapper.getArticlesByCategory(
				category.getName(),
				new RowBounds(pageIndex, pageSize));
		return new PageInfo<Article>(list);
	}

	public PageInfo<Article> findArticlesFromTag(Tag tag, int pageIndex,
			int pageSize) {
	    List<Article> list = articleMapper.getArticlesByTags(tag.getName(),
		        new RowBounds(pageIndex, pageSize));
	    return new PageInfo<Article>(list);
	}

	public HashMap<String, Article> findPrevAndNextArticle(Article article) {
		HashMap<String, Article> prevNextArticle = new HashMap<String, Article>();
		List<Article> prevArticle = articleMapper.getPrevArticles(article
				.getCreatedDate());
		List<Article> nextArticle = articleMapper.getNextArticles(article
				.getCreatedDate());
		prevNextArticle.put("prev_article",
				prevArticle.size() > 0 ? prevArticle.get(0) : null);
		prevNextArticle.put("next_article",
				nextArticle.size() > 0 ? nextArticle.get(0) : null);
		return prevNextArticle;
	}

	public List<HashMap<String, Object>> findArticlesInSameTag(Article article,
			int pageIndex, int pageSize) {
		List<HashMap<String, Object>> relate_tags = new ArrayList<HashMap<String, Object>>();
		for (Article m : articleMapper.getArticlesInSameTag(article.getId(),
		        new RowBounds(pageIndex, pageSize))) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", m.getId());
			temp.put("title", m.getTitle());
			relate_tags.add(temp);
		}
		return relate_tags;
	}

    public void updateArticlePV(Article article) {
        articleMapper.updatePV(article);
        
    }
}
