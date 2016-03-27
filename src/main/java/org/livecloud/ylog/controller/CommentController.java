package org.livecloud.ylog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.livecloud.ylog.entity.Article;
import org.livecloud.ylog.entity.Comment;
import org.livecloud.ylog.vo.CommentInfo;
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

@Controller("commentController")  
@RequestMapping("/admin")
public class CommentController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/comment")
	public @ResponseBody Comment addComment(@RequestBody CommentInfo commentInfo) {
		int postId = commentInfo.getPostId();
		Article article = articleService.findById(postId);
		return commentService.addNewComment(commentInfo, article);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/comment/{id}")
	public @ResponseBody Comment getComment(@PathVariable String id) {
		Comment comment = commentService.findById(Integer.parseInt(id));
		return comment;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/comment")
	public @ResponseBody Map<String, Object> getAllComments(HttpServletRequest request,
			@ModelAttribute("options") HashMap<String, String> options) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(options.get("adminPageSize"),0);
		int articleId = StringHelper.parseWithDefault(request.getParameter("id"),0);
		PageInfo<Comment> comments;
		if(articleId==0) {
			comments = commentService.findLatestComments(page, pageSize);
		} else {
			comments = commentService.findAllByArticle(articleId, page, pageSize);
		}
		Map<String, Object> returnValues = new HashMap<String, Object>();
		List<Object> articleList = new ArrayList<Object>();
		for (Comment comment : comments.getList()){
			HashMap<String, String> articleJson = new HashMap<String, String>();
			articleJson.put("id", comment.getId()+"");
			articleJson.put("content", comment.getContent());
			articleJson.put("user", comment.getAuthor());
			articleJson.put("date", StringHelper.dateToString(comment.getPosttime()));
			articleJson.put("email", comment.getEmail());
			articleJson.put("url", comment.getHomepage());
			articleList.add(articleJson);
		}
		returnValues.put("comment", articleList);
		returnValues.put("totalElements", comments.getTotal());
		returnValues.put("static", getOptions());
		return returnValues;
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/comment/{id}")
	public @ResponseBody Comment updateComment(@RequestBody Comment commentInfo, @PathVariable String id) {
		Comment comment = commentService.findById(Integer.parseInt(id));
		comment.setContent(commentInfo.getContent());
		return commentService.updateComment(comment);
	}

	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/comment/{id}")
	public @ResponseBody Map<String, Object> removeComment(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", commentService.removeCommentById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/comment")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {   
        	commentService.removeCommentById(items[i]);
        }  
    }  
}
