package org.livecloud.ylog.controller;

import java.util.HashMap;
import java.util.List;

import org.livecloud.ylog.entity.Category;
import org.livecloud.ylog.entity.User;
import org.livecloud.ylog.service.ArticleService;
import org.livecloud.ylog.service.CategoryService;
import org.livecloud.ylog.service.CommentService;
import org.livecloud.ylog.service.OptionsService;
import org.livecloud.ylog.service.TagService;
import org.livecloud.ylog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value={"categories", "users", "options"})
public class BaseController {
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected OptionsService optionsService;
	
	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected CommentService commentService;
	
	@Autowired
	protected TagService tagService;
	
	@Autowired
	protected UserService userService;
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryService.findAllCategories();
	}
	
	@ModelAttribute("users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	@ModelAttribute("options")
	public HashMap<String, String> getOptions() {
		return optionsService.findAllOptions();
	}
}
