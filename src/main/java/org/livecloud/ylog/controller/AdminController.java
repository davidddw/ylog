package org.livecloud.ylog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.livecloud.ylog.utils.ImageHelper; 
import org.livecloud.ylog.utils.StringHelper;

@Controller("adminController")  
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("action","admin");
		return "admin/dashboard";
	}
    
    @RequestMapping(value = "/addArticle")
	public String addArticlePage(HttpServletRequest request, Model modelMap) {
    	modelMap.addAttribute("action","addarticle");
		return "admin/saveArticle";
	}

	@RequestMapping(value = "/listArticle")
	public String listArticlePage(@ModelAttribute("options") HashMap<String, String> options, 
			HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("tatal",articleService.getArticleCount());
		modelMap.addAttribute("pageSize",options.get("adminPageSize"));
		modelMap.addAttribute("action","listarticle");
		return "admin/listArticle";
	}
	
	@RequestMapping(value = "/listCategory")
	public String listCategory(@ModelAttribute("options") HashMap<String, String> options, 
			HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("total",categoryService.getCategoryCount());
		modelMap.addAttribute("pageSize",options.get("adminPageSize"));
		modelMap.addAttribute("action","listcategorie");
		return "admin/listCategory";
	}
	
	@RequestMapping(value = "/listSetting")
	public String listSetting(@ModelAttribute("options") HashMap<String, String> options, 
			HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("action","listsetting");
		return "admin/listSetting";
	}
	
	@RequestMapping(value = "/listComment")
	public String listComment(@ModelAttribute("options") HashMap<String, String> options, 
			HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("total",commentService.getCommentCount());
		modelMap.addAttribute("pageSize",options.get("adminPageSize"));
		modelMap.addAttribute("action","listcomment");
		return "admin/listComment";
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getTotalCommentByArticle")
	public @ResponseBody Map<String, Object> getTotalComment(HttpServletRequest request, Model modelMap) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		int articleId = StringHelper.parseWithDefault(request.getParameter("id"),0);
		returnValues.put("count", commentService.getCommentCountByArticle(articleId));
		return returnValues;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getTotalArticleByCategory")
	public @ResponseBody Map<String, Object> getTotalArticle(HttpServletRequest request, Model modelMap) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		int categoryId = StringHelper.parseWithDefault(request.getParameter("id"),0);
		returnValues.put("count", articleService.getArticleCountByCategory(categoryId));
		return returnValues;
	}
	
	@RequestMapping(value = "/listUser")
	public String listUser(@ModelAttribute("options") HashMap<String, String> options, 
			HttpServletRequest request, Model modelMap) {
		modelMap.addAttribute("total",userService.getUserCount());
		modelMap.addAttribute("pageSize",options.get("adminPageSize"));
		modelMap.addAttribute("action","listuser");
		return "admin/listUser"; 
	}
	
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> handleImport(HttpServletRequest request,
			@RequestParam(value = "imgFile", required = true) MultipartFile file) { 
		Map<String, Object> returnValues = null;
		try {	
			String realPath = ImageHelper.getImageRealPath(request,"static/upload/");
			String urlPath = ImageHelper.getImageUrlPath(request,"static/upload/");
			returnValues = ImageHelper.uploadOriginalImage(file, realPath, urlPath);
		} catch (Exception e) {
			returnValues.put("error", 1);
			returnValues.put("msg", "fail");
			e.printStackTrace();
			return returnValues;
		}
		returnValues.put("error", 0);
		return returnValues;
	}
}
