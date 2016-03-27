package org.livecloud.ylog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.livecloud.ylog.entity.User;
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

@Controller("userController")  
@RequestMapping("/admin")
public class UserController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/user")
	public @ResponseBody long addUser(@RequestBody User user) {
		return userService.addNewUser(user);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/user/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {
		User user = userService.findById(Integer.parseInt(id));
		return user;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public @ResponseBody Map<String, Object> getAllUsers(HttpServletRequest request,
			@ModelAttribute("options") HashMap<String, String> options) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(options.get("adminPageSize"),0);
		PageInfo<User> users = userService.findAllUsers(page, pageSize);
		Map<String, Object> returnValues = new HashMap<String, Object>();
		List<Object> userList = new ArrayList<Object>();
		
		for (User user : users.getList()){
			HashMap<String, String> userJson = new HashMap<String, String>();
			userJson.put("id", user.getId()+"");
			userJson.put("name", user.getName());
			userJson.put("status", String.valueOf(user.getStatus()));
			userJson.put("date", StringHelper.dateToString(user.getLastvisit()));
			userJson.put("email", user.getEmail());
			userList.add(userJson);
		}
		returnValues.put("user", userList);
		returnValues.put("totalElements", users.getTotal());
		returnValues.put("static", getOptions());
		return returnValues;
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/user/{id}")
	public @ResponseBody long updateUser(@RequestBody User user, @PathVariable String id) {
		return userService.updateUser(user);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/user/{id}")
	public @ResponseBody Map<String, Object> removeUser(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", userService.removeUserById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/user")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {    
        	articleService.removeArticleById(items[i]);  
        }  
    }  
}
