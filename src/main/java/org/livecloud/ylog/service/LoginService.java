package org.livecloud.ylog.service;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean loginIn(String username, String password,HttpServletRequest request) throws Exception {		
		// subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 记录该令牌，如果不记录则类似购物车功能不能使用。
		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException ex) {
			request.setAttribute("msg", "用户不存在");
		} catch (IncorrectCredentialsException ex) {
			request.setAttribute("msg", "密码错误");
		} catch (AuthenticationException e) {
			request.setAttribute("msg", "服务器忙...");
		}
		// 验证是否成功登录的方法
		if (subject.isAuthenticated()) {
			session.setAttribute("user", username);
			return true;
		}
		return false;
	}

	public boolean loginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return true;
	}
}
