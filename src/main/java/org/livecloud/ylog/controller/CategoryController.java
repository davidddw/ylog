package org.livecloud.ylog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.livecloud.ylog.entity.Category;
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

@Controller("categoryController")  
@RequestMapping("/admin")
public class CategoryController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/category")
	public @ResponseBody long addCategory(@RequestBody Category categoryInfo) {
		return categoryService.addNewCategory(categoryInfo);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/category/{id}")
	public @ResponseBody Category getCategory(@PathVariable String id) {
		Category category = categoryService.findById(Integer.parseInt(id));
		return category;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/category")
	public @ResponseBody Map<String, Object> getAllCategories(HttpServletRequest request,
			@ModelAttribute("options") HashMap<String, String> options) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(options.get("adminPageSize"),0);
		PageInfo<Category> categories = categoryService.findAllCategories(page, pageSize);
		Map<String, Object> returnValues = new HashMap<String, Object>();
		List<Object> categoryList = new ArrayList<Object>();
		for (Category category : categories.getList()){
			HashMap<String, String> categoryJson = new HashMap<String, String>();
			categoryJson.put("id", category.getId()+"");
			categoryJson.put("enName", category.getName());
			categoryJson.put("cnName", category.getIntro());
			categoryList.add(categoryJson);
		}
		returnValues.put("category", categoryList);
		returnValues.put("totalElements", categories.getTotal());
		returnValues.put("static", getOptions());
		return returnValues;
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/category/{id}")
	public @ResponseBody long updateCategory(@RequestBody Category categoryInfo, @PathVariable String id) {
		return categoryService.updateCategory(categoryInfo);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/category/{id}")
	public @ResponseBody Map<String, Object> removeArticle(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", categoryService.removeCategoryById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/category")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {   
        	categoryService.removeCategoryById(items[i]);
        }  
    }  
}
