package com.equality.springbootdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equality.springbootdemo2.entity.two.UserInfo;
import com.equality.springbootdemo2.service.UserInfoService;



@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	
	
	@Autowired
	private UserInfoService userService;
	
	@RequestMapping("/findById")
	@ResponseBody
	public UserInfo findById(@RequestParam Integer id){
		return userService.findById(id);
	}
	
	@RequestMapping("/add")
	public String add(){
		return "user/add";
	}
	
	@RequestMapping("/update")
	public String update(){
		return "user/update";
	}

}
