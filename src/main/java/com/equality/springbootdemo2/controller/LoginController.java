package com.equality.springbootdemo2.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	/**
	 * 测试 thymeleaf
	 * @return
	 */
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model){
		
		model.addAttribute("name", "test");
		
		return "test";
	}
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.isAuthenticated()) {
			System.out.println(subject.getSession().getId()+"," + subject.getSession().getLastAccessTime() +","+subject.getSession().getTimeout());
		}		
		subject.logout();
		return "login";
	}
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/noAuth")
	public String noAuth(){
		return "noAuth";
	}
	
	/**
	 * 登录确认
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String name,String password,Model model){
		
		/**
		 * 使用Shiro编写认证操作
		 */
		//1.获取subject
		Subject subject = SecurityUtils.getSubject();
		
		//未验证
		if (subject.isAuthenticated()) {
			System.out.println(subject.getSession().getId()+"," + subject.getSession().getStartTimestamp() +","+subject.getSession().getTimeout());
			return "redirect:/testThymeleaf";
		}
		
		//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		//token.setRememberMe(true);
		
		//3.执行登录方法
		try {
			subject.login(token);
			System.out.println(subject.getSession().getId()+"," + subject.getSession().getStartTimestamp() +","+subject.getSession().getTimeout());
			//登录成功
			return "redirect:/testThymeleaf";
		} catch (UnknownAccountException e) {
			//用户名不存在
			model.addAttribute("msg", "用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//密码错误
			model.addAttribute("msg", "用户名/密码错误");
			return "login";
		}
		
	}

}
