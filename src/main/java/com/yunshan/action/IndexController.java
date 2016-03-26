package com.yunshan.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunshan.bean.User;
import com.yunshan.service.UserService;

@Controller
@RequestMapping("/rest")
public class IndexController  {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User get(@PathVariable("id") Integer id) {
        return userService.queryById(id);
    }
    
    @RequestMapping(value = "/huser/{id}", method = RequestMethod.GET)
    public String gethtml(@PathVariable("id") Integer id, HttpServletRequest request, Model modelMap) {
        User user = userService.queryById(id);
        modelMap.addAttribute("user", user);
        return "index";
    }
}
