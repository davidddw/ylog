package org.livecloud.ylog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.livecloud.ylog.entity.Article;
import org.livecloud.ylog.vo.ArticleInfo;
import org.livecloud.ylog.utils.StringHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@Controller("articleController")  
@RequestMapping("/admin")
public class ArticleController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/article")
	public @ResponseBody long addArticle(@RequestBody ArticleInfo articleInfo) {
		return articleService.addNewArticleFromInfo(articleInfo);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/article/{id}")
	public @ResponseBody Article getArticle(@PathVariable String id) {
		Article article = articleService.findById(Integer.parseInt(id));
		return article;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/article")
	public @ResponseBody Map<String, Object> getAllArticle(HttpServletRequest request,
			@ModelAttribute("options") HashMap<String, String> options) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(options.get("adminPageSize"),0);
		int categoryId = StringHelper.parseWithDefault(request.getParameter("id"), 0);
		PageInfo<Article> articles;
		if(categoryId==0) {
			articles = articleService.findLatestArticles(page, pageSize);
		} else {
			articles = articleService.findArticlesByCategoryId(categoryId, page, pageSize);
		}
		Map<String, Object> returnValues = new HashMap<String, Object>();
		List<Object> articleList = new ArrayList<Object>();
		for (Article article : articles.getList()){
			HashMap<String, String> articleJson = new HashMap<String, String>();
			articleJson.put("id", article.getId()+"");
			articleJson.put("title", article.getTitle());
			articleJson.put("user", null == article.getUser()?"null":article.getUser().getName());
			articleJson.put("date", StringHelper.dateToString(article.getCreatedDate()));
			articleJson.put("category", null==article.getCategory()?"null":article.getCategory().getName());
			articleList.add(articleJson);
		}
		returnValues.put("article", articleList);
		returnValues.put("totalElements", articles.getTotal());
		returnValues.put("static", getOptions());
		return returnValues;
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/article/{id}")
	public @ResponseBody long updateArticle(@RequestBody ArticleInfo articleInfo, @PathVariable String id) {
		return articleService.updateArticleFromInfo(articleInfo);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/article/{id}")
	public @ResponseBody Map<String, Object> removeArticle(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", articleService.removeArticleById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/article")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {    
        	articleService.removeArticleById(items[i]);  
        }  
    }  
}
